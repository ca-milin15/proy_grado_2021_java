package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.autenticacion.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.almacenamiento.AlmacenamientoService;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.autenticacion.beans.AutenticacionBiometricaAWSResponse;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.autenticacion.beans.AutenticacionBiometricaResponse;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.registro.beans.RegistroBiometriaAWSRequest;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.registro.beans.RegistroBiometriaAWSRequest.ObjectRequest;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.config.AWSPropiedadesSistema;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.utilidades.Utilidades;
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
	
	@Override
	public AutenticacionBiometricaResponse autenticar(MultipartFile multipartFile) {
		try {
			var fotografia = Utilidades.crearArchivoDesdeMultipart(multipartFile);
			var cargarArchivoS3Respuesta = almacenamientoService.cargarObjeto(
					awsPropiedadesSistema.getAutenticacion().getBucketName(), 
					awsPropiedadesSistema.getAutenticacion().getBucketFileContext(),
					fotografia
			);
			log.info("autenticar: ".concat(objectMapper.writeValueAsString(cargarArchivoS3Respuesta)));
			procesoConsumoAutenticacionDatosBiometricosAWS(
					RegistroBiometriaAWSRequest.builder()
						.objectRequest(
							ObjectRequest.builder()
										 .imageBucketName(awsPropiedadesSistema.getRegistro().getBucketFileContext().concat(multipartFile.getOriginalFilename()))
										 .imageS3Bucket(awsPropiedadesSistema.getRegistro().getBucketName() )
										 .build()
					    )
						.operationType("AUTH")
						.build()
			);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	private void procesoConsumoAutenticacionDatosBiometricosAWS(RegistroBiometriaAWSRequest registroBiometriaAWSRequest) {
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
			System.out.println("autenticacionBiometricaAWSResponse: ".concat(objectMapper.writeValueAsString(autenticacionBiometricaAWSResponse)));
		} catch (IOException e) {
			throw new RuntimeException();
		}
		
	}

}
