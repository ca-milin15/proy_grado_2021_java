package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.autenticacion.service;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.administracion.usuario.beans.UsuarioResponse;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.almacenamiento.AlmacenamientoService;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.autenticacion.beans.AutenticacionBiometricaAWSResponse;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.autenticacion.beans.AutenticacionDTO;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.registro.beans.RegistroBiometriaAWSRequest;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.registro.beans.RegistroBiometriaAWSRequest.ObjectRequest;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.service.UsuarioInfoBiometricaService;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.config.AWSPropiedadesSistema;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.utilidades.Utilidades;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.utilidades.enums.MessageStaticClass;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.utilidades.exception.RostroNoEncontradoRuntimeException;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.utilidades.exception.UsuarioInfoBiometricaConsultaErrorRuntimeException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class AWSAutenticacionServiceImpl implements AutenticacionService {

	AlmacenamientoService almacenamientoService;
	ObjectMapper objectMapper;
	AWSPropiedadesSistema awsPropiedadesSistema;
	UsuarioInfoBiometricaService usuarioInfoBiometricaService;
	Environment environment;

	private final static Integer UMBRAL_SIMILIRIDAD = 98;

	@Override
	public AutenticacionDTO autenticar(MultipartFile multipartFile) {
		try {
			var fotografia = Utilidades.crearArchivoDesdeMultipart(multipartFile);
			var cargarArchivoS3Respuesta = almacenamientoService.cargarObjeto(
					awsPropiedadesSistema.getAutenticacion().getBucketName(),
					awsPropiedadesSistema.getAutenticacion().getBucketFileContext(), fotografia);
			System.out.println("autenticar: ".concat(objectMapper.writeValueAsString(cargarArchivoS3Respuesta)));
			log.info("autenticar: ".concat(objectMapper.writeValueAsString(cargarArchivoS3Respuesta)));
			var autenticacionBiometricaAWSResponse = procesoConsumoAutenticacionDatosBiometricosAWS(
					RegistroBiometriaAWSRequest.builder()
							.objectRequest(ObjectRequest.builder()
									.imageBucketName(awsPropiedadesSistema.getAutenticacion().getBucketFileContext()
											.concat(multipartFile.getOriginalFilename()))
									.imageS3Bucket(awsPropiedadesSistema.getAutenticacion().getBucketName()).build())
							.operationType("AUTH").build());
			return procesarRespuesta(autenticacionBiometricaAWSResponse);
		} catch (Exception e) {
			log.error(ExceptionUtils.getMessage(e));
			log.error(ExceptionUtils.getRootCauseMessage(e));
			throw new RostroNoEncontradoRuntimeException(ExceptionUtils.getMessage(e));
		}
	}

	private AutenticacionDTO procesarRespuesta(AutenticacionBiometricaAWSResponse autenticacionBiometricaAWSResponse) {
		if (!ObjectUtils.isEmpty(autenticacionBiometricaAWSResponse.getBody())) {
			var rostrosEncontrados = autenticacionBiometricaAWSResponse.getBody().getFaceMatches();
			log.info("Limite del umbral: ".concat(UMBRAL_SIMILIRIDAD.toString()));
			if (!CollectionUtils.isEmpty(rostrosEncontrados)) {
				log.info("1");
				var rostroEncontrado = rostrosEncontrados.stream()
						.filter(rostroNull -> !ObjectUtils.isEmpty(rostroNull)
								&& !ObjectUtils.isEmpty(rostroNull.getFace())
								&& !ObjectUtils.isEmpty(rostroNull.getFace().getExternalImageId()))
						.filter(rostro -> rostro.getSimilarity() >= UMBRAL_SIMILIRIDAD).findFirst()
						.orElseThrow(() -> new RostroNoEncontradoRuntimeException(
								MessageStaticClass.ERR_ROSTRO_NO_CUMPLE_REQ.getMensaje()));

				log.info("2");
				var infoBiometrica = usuarioInfoBiometricaService.buscarInfoBiometricaPorUsuario(
						BigInteger.valueOf(Long.valueOf(rostroEncontrado.getFace().getExternalImageId().toString())));

				if (ObjectUtils.isEmpty(infoBiometrica)) {
					log.error("getExternalImageId".concat(rostroEncontrado.getFace().getExternalImageId().toString()));
					throw new UsuarioInfoBiometricaConsultaErrorRuntimeException(
							MessageStaticClass.ERR_USUARIO_INFO_BIOM_CONSULTA.getMensaje());
				}

				log.info("3");
				var descargarArchivoS3Respuesta = almacenamientoService.descargarObjeto(infoBiometrica.getRutaFoto(),
						infoBiometrica.getNombreFotografia());
				log.info("4");
				log.info("descargarArchivoS3Respuesta: ".concat(descargarArchivoS3Respuesta.toString()));
				return AutenticacionDTO.builder().archivoAutenticacion(descargarArchivoS3Respuesta).usuarioResponse(new UsuarioResponse(infoBiometrica.getUsuario())).build();
			} else {
				throw new RostroNoEncontradoRuntimeException(MessageStaticClass.ERR_ROSTRO_NO_ENCONTRADO.getMensaje());
			}
		} else {
			throw new RostroNoEncontradoRuntimeException(MessageStaticClass.ERR_ROSTRO_NO_ENCONTRADO.getMensaje());
		}
	}

	private AutenticacionBiometricaAWSResponse procesoConsumoAutenticacionDatosBiometricosAWS(
			RegistroBiometriaAWSRequest registroBiometriaAWSRequest) {
		try {
			var endPointAutenticacion = new StringBuilder()
					.append(awsPropiedadesSistema.getAwsApiService().getContext()
							.concat(environment.getActiveProfiles()[0]).concat("/"))
					.append(awsPropiedadesSistema.getAwsApiService().getRegistro().getEndPoint()).toString();
			log.info("Request endPointRegistro: ".concat(endPointAutenticacion));
			var client = HttpClient.newBuilder().version(Version.HTTP_2).build();
			var httpRequest = HttpRequest.newBuilder(URI.create(endPointAutenticacion))
					.POST(BodyPublishers.ofString(objectMapper.writeValueAsString(registroBiometriaAWSRequest)))
					.build();
			var response = client.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString());
			var autenticacionBiometricaAWSResponse = objectMapper.readValue(response.join().body(),
					AutenticacionBiometricaAWSResponse.class);
			log.info("autenticacionBiometricaAWSResponse: "
					.concat(objectMapper.writeValueAsString(autenticacionBiometricaAWSResponse)));
			return autenticacionBiometricaAWSResponse;
		} catch (IOException e) {
			log.error(ExceptionUtils.getMessage(e));
			log.error(ExceptionUtils.getRootCauseMessage(e));
			throw new RuntimeException(MessageStaticClass.ERR_CONSUMO_AUTENTICACION.getMensaje());
		}

	}

}
