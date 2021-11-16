package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.registro.service;

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
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.administracion.usuario.service.UsuarioService;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.almacenamiento.AlmacenamientoService;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.model.UsuarioInfoBiometrica;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.registro.beans.RegistroBiometriaAWSRequest;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.registro.beans.RegistroBiometriaAWSRequest.ObjectRequest;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.registro.beans.RegistroBiometriaAWSResponse;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.registro.beans.RegistroBiometriaResponse;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.service.UsuarioInfoBiometricaService;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.config.AWSPropiedadesSistema;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.utilidades.Utilidades;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.utilidades.enums.MessageStaticClass;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.utilidades.exception.UsuarioInfoBiometricaExistenteRuntimeException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class AWSRegistroServiceImpl implements RegistroService {

	UsuarioService usuarioService;
	AlmacenamientoService almacenamientoService;
	ObjectMapper objectMapper;
	AWSPropiedadesSistema awsPropiedadesSistema;
	UsuarioInfoBiometricaService usuarioInfoBiometricaService;
	Environment environment;

	@Override
	public RegistroBiometriaResponse registrarDatosBiometricos(MultipartFile multipartFileFotografia,
			BigInteger idUsuario) {
		try {
			var usuario = usuarioService.buscarUsuarioPorId(idUsuario);
			var infoBiom = usuario.getUsuarioInfoBiometrica();
			if (ObjectUtils.isEmpty(infoBiom)) {
				var fotografia = Utilidades.crearArchivoDesdeMultipart(multipartFileFotografia);
				var cargarArchivoS3Respuesta = almacenamientoService.cargarObjeto(
						awsPropiedadesSistema.getRegistro().getBucketName(),
						awsPropiedadesSistema.getRegistro().getBucketFileContext(), fotografia);
				log.info(
						"cargarArchivoS3Respuesta: ".concat(objectMapper.writeValueAsString(cargarArchivoS3Respuesta)));
				var imageBucketName = awsPropiedadesSistema.getRegistro().getBucketFileContext()
						.concat(multipartFileFotografia.getOriginalFilename());
				var procesoRegistroRes = procesoConsumoRegistroDatosBiometricosAWS(RegistroBiometriaAWSRequest.builder()
						.objectRequest(ObjectRequest.builder().imageBucketName(imageBucketName)
								.imageS3Bucket(awsPropiedadesSistema.getRegistro().getBucketName())
								.externalId(usuario.getId().toString()).build())
						.operationType("SIGNUP").build());
				var usuarioBiometria = UsuarioInfoBiometrica.builder().idExternal(usuario.getId().toString())
						.usuario(usuario).idRostro(procesoRegistroRes.getRostroId())
						.rutaFoto(awsPropiedadesSistema.getRegistro().getBucketName()).nombreFotografia(imageBucketName)
						.build();
				usuarioInfoBiometricaService.almacenarEntidad(usuarioBiometria);
				return null;
			} else {
				throw new UsuarioInfoBiometricaExistenteRuntimeException(MessageStaticClass.ERR_USUARIO_INFO_BIOM_EXISTENTE.getMensaje());
			}
		} catch (JsonProcessingException e) {
			throw new RuntimeException(ExceptionUtils.getMessage(e));
		}
	}

	private RegistroBiometriaResponse procesoConsumoRegistroDatosBiometricosAWS(
			RegistroBiometriaAWSRequest registroBiometriaAWSRequest) {
		try {
			var endPointRegistro = new StringBuilder()
					.append(awsPropiedadesSistema.getAwsApiService().getContext()
							.concat(environment.getActiveProfiles()[0]).concat("/"))
					.append(awsPropiedadesSistema.getAwsApiService().getRegistro().getEndPoint()).toString();
			log.info("Request endPointRegistro: ".concat(endPointRegistro));
			var client = HttpClient.newBuilder().version(Version.HTTP_2).build();
			var httpRequest = HttpRequest.newBuilder(URI.create(endPointRegistro))
					.POST(BodyPublishers.ofString(objectMapper.writeValueAsString(registroBiometriaAWSRequest)))
					.build();
			var response = client.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString());
			var registroBiometriaAWSResponse = objectMapper.readValue(response.join().body(),
					RegistroBiometriaAWSResponse.class);
			if (!ObjectUtils.isEmpty(registroBiometriaAWSResponse.getErrorMessage())) {
				throw new RuntimeException(registroBiometriaAWSResponse.getErrorMessage());
			} else {
				return RegistroBiometriaResponse.builder().rostroId(registroBiometriaAWSResponse.getBody().getFaceId())
						.build();
			}
		} catch (IOException e) {
			throw new RuntimeException();
		}

	}

}
