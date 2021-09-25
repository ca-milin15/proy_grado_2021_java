package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.registro.service;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.almacenamiento.AlmacenamientoService;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.registro.beans.RegistroBiometriaResponse;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.config.PropiedadesSistema;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.utilidades.Utilidades;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class RegistroServiceImpl implements RegistroService{

	AlmacenamientoService almacenamientoService;
	ObjectMapper objectMapper;
	PropiedadesSistema propiedadesSistema;

	@Override
	public RegistroBiometriaResponse registrarDatosBiometricos(MultipartFile multipartFileFotografia) {
		try {
			var fotografia = Utilidades.crearArchivoDesdeMultipart(multipartFileFotografia);
			var cargarArchivoS3Respuesta = almacenamientoService.cargarObjeto(
					propiedadesSistema.getAutenticacion().getBucketName(), 
					propiedadesSistema.getAutenticacion().getBucketFileContext(),
					fotografia
			);
			log.info("cargarArchivoS3Respuesta: ".concat(objectMapper.writeValueAsString(cargarArchivoS3Respuesta)));
			return null; 
		} catch (JsonProcessingException e) {
			throw new RuntimeException();
		}
	}
	

}
