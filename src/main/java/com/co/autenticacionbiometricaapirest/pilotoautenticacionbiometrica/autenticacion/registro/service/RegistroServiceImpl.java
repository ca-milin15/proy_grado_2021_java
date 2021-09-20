package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.registro.service;

import org.springframework.stereotype.Service;

import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.almacenamiento.AlmacenamientoService;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.registro.beans.RegistroBiometriaRequest;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.registro.beans.RegistroBiometriaResponse;
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

	@Override
	public RegistroBiometriaResponse registrarDatosBiometricos(RegistroBiometriaRequest registroBiometriaRequest) {
		try {
			var cargarArchivoS3Respuesta = almacenamientoService.cargarObjeto(registroBiometriaRequest.getFotografiaBase64());
			log.info("cargarArchivoS3Respuesta: ".concat(objectMapper.writeValueAsString(cargarArchivoS3Respuesta)));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	

}
