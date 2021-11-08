package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.autenticacion.service;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.nio.file.Path;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.almacenamiento.AlmacenamientoService;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.autenticacion.beans.AutenticacionBiometricaAWSResponse;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.registro.beans.RegistroBiometriaAWSRequest;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.registro.beans.RegistroBiometriaAWSRequest.ObjectRequest;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.service.UsuarioInfoBiometricaService;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.config.AWSPropiedadesSistema;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.utilidades.Utilidades;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.utilidades.enums.MessageStaticClass;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.utilidades.exception.RostroNoEncontradoRuntimeException;
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

	
	private final static Integer UMBRAL_SIMILIRIDAD = 98;
	
	@Override
	public Path autenticar(MultipartFile multipartFile) {
		try {
			var fotografia = Utilidades.crearArchivoDesdeMultipart(multipartFile);
			var cargarArchivoS3Respuesta = almacenamientoService.cargarObjeto(
					awsPropiedadesSistema.getAutenticacion().getBucketName(), 
					awsPropiedadesSistema.getAutenticacion().getBucketFileContext(),
					fotografia
			);
			System.out.println("autenticar: ".concat(objectMapper.writeValueAsString(cargarArchivoS3Respuesta)));
			log.info("autenticar: ".concat(objectMapper.writeValueAsString(cargarArchivoS3Respuesta)));
			var autenticacionBiometricaAWSResponse = procesoConsumoAutenticacionDatosBiometricosAWS(
					RegistroBiometriaAWSRequest.builder()
						.objectRequest(
							ObjectRequest.builder()
										 .imageBucketName(awsPropiedadesSistema.getAutenticacion().getBucketFileContext().concat(multipartFile.getOriginalFilename()))
										 .imageS3Bucket(awsPropiedadesSistema.getAutenticacion().getBucketName() )
										 .build()
					    )
						.operationType("AUTH")
						.build()
			);
			return procesarRespuesta(autenticacionBiometricaAWSResponse);
		} catch (Exception e) {
			log.error(ExceptionUtils.getMessage(e));
			log.error(ExceptionUtils.getRootCauseMessage(e));
			throw new RuntimeException(MessageStaticClass.ERR_PROCESO_AUTENTICACION.getMensaje());
		}
	}
	
	private Path procesarRespuesta(
			AutenticacionBiometricaAWSResponse autenticacionBiometricaAWSResponse) {
		var rostrosEncontrados = autenticacionBiometricaAWSResponse.getBody().getFaceMatches();

		log.info("Limite del umbral: ".concat(UMBRAL_SIMILIRIDAD.toString()));
		if(!CollectionUtils.isEmpty(rostrosEncontrados)) {
			var rostroEncontrado = rostrosEncontrados.stream().filter(rostro -> !ObjectUtils.isEmpty(rostro.getFace().getExternalImageId()) && rostro.getSimilarity() >= UMBRAL_SIMILIRIDAD).findFirst().orElseThrow(() -> new RostroNoEncontradoRuntimeException(MessageStaticClass.ERR_ROSTRO_NO_CUMPLE_REQ.getMensaje()));
			var infoBiometrica = usuarioInfoBiometricaService.buscarInfoBiometricaPorUsuario(BigInteger.valueOf(Long.valueOf(rostroEncontrado.getFace().getExternalImageId().toString())));
			var descargarArchivoS3Respuesta = almacenamientoService.descargarObjeto(
					infoBiometrica.getRutaFoto(), 
					infoBiometrica.getNombreFotografia()
			);
			log.info("descargarArchivoS3Respuesta: ".concat(descargarArchivoS3Respuesta.toString()));
			return descargarArchivoS3Respuesta;
		} else {
			throw new RostroNoEncontradoRuntimeException(MessageStaticClass.ERR_ROSTRO_NO_ENCONTRADO.getMensaje());
		}
	}

	private AutenticacionBiometricaAWSResponse procesoConsumoAutenticacionDatosBiometricosAWS(RegistroBiometriaAWSRequest registroBiometriaAWSRequest) {
		try {
			var endPointRegistro = new StringBuilder()
					.append(awsPropiedadesSistema.getAwsApiService().getContext())
					.append(awsPropiedadesSistema.getAwsApiService().getRegistro().getEndPoint())
					.toString();
			var client = HttpClient.newBuilder().version(Version.HTTP_2).build();
			var httpRequest = HttpRequest.newBuilder(
					URI.create(endPointRegistro))
					.POST(BodyPublishers.ofString(objectMapper.writeValueAsString(registroBiometriaAWSRequest)))
					.build();
			var response = client.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString());
			var autenticacionBiometricaAWSResponse = objectMapper.readValue(response.join().body(), AutenticacionBiometricaAWSResponse.class);
			log.info("autenticacionBiometricaAWSResponse: ".concat(objectMapper.writeValueAsString(autenticacionBiometricaAWSResponse)));
			return autenticacionBiometricaAWSResponse;
		} catch (IOException e) {
			log.error(ExceptionUtils.getMessage(e));
			log.error(ExceptionUtils.getRootCauseMessage(e));
			throw new RuntimeException(MessageStaticClass.ERR_CONSUMO_AUTENTICACION.getMensaje());
		}
		
	}

}
