package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.registro.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.registro.beans.RegistroBiometriaRequest;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.registro.beans.RegistroBiometriaResponse;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.registro.service.RegistroService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("registro")
public class RegistroController {

	RegistroService registroService;
	
	@ResponseStatus(value = HttpStatus.OK)
	@PostMapping("registrar-datos-biometricos")
	public RegistroBiometriaResponse registrarDatosBiometricos(RegistroBiometriaRequest registroBiometriaRequest){
		return registroService.registrarDatosBiometricos(registroBiometriaRequest);
	}
}
