package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.registro.service;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.almacenamiento.AlmacenamientoService;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.registro.beans.RegistroBiometriaAWSRequest;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.registro.beans.RegistroBiometriaAWSRequest.ObjectRequest;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.registro.beans.RegistroBiometriaAWSResponse;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.registro.beans.RegistroBiometriaResponse;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.config.AWSPropiedadesSistema;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.utilidades.Utilidades;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class AWSRegistroServiceImpl implements RegistroService{

	AlmacenamientoService almacenamientoService;
	ObjectMapper objectMapper;
	AWSPropiedadesSistema awsPropiedadesSistema;

	@Override
	public RegistroBiometriaResponse registrarDatosBiometricos(MultipartFile multipartFileFotografia) {
		try {
			var fotografia = Utilidades.crearArchivoDesdeMultipart(multipartFileFotografia);
			var cargarArchivoS3Respuesta = almacenamientoService.cargarObjeto(
					awsPropiedadesSistema.getRegistro().getBucketName(), 
					awsPropiedadesSistema.getRegistro().getBucketFileContext(),
					fotografia
			);
			log.info("cargarArchivoS3Respuesta: ".concat(objectMapper.writeValueAsString(cargarArchivoS3Respuesta)));
			procesoConsumoRegistroDatosBiometricosAWS(
					RegistroBiometriaAWSRequest.builder()
						.objectRequest(
							ObjectRequest.builder()
										 .imageBucketName(awsPropiedadesSistema.getRegistro().getBucketFileContext().concat(multipartFileFotografia.getOriginalFilename()))
										 .imageS3Bucket(awsPropiedadesSistema.getRegistro().getBucketName() )
										 .build()
					    )
						.operationType("SIGNUP")
						.build()
			);
			return null; 
		} catch (JsonProcessingException e) {
			throw new RuntimeException();
		}
	}

	private RegistroBiometriaResponse procesoConsumoRegistroDatosBiometricosAWS(RegistroBiometriaAWSRequest registroBiometriaAWSRequest) {
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
			var registroBiometriaAWSResponse = objectMapper.readValue(response.join().body(), RegistroBiometriaAWSResponse.class);
			if(!ObjectUtils.isEmpty(registroBiometriaAWSResponse.getErrorMessage())) {
				throw new RuntimeException(registroBiometriaAWSResponse.getErrorMessage());
			} else {
				return RegistroBiometriaResponse.builder().fotografia("").build();
			}
		} catch (IOException e) {
			throw new RuntimeException();
		}
		
	}
	

}
