package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.autenticacion.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.autenticacion.beans.AutenticacionBiometricaResponse;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.autenticacion.service.AutenticacionService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("autenticacion")
public class AutenticacionController {

	AutenticacionService autenticacionService;
	
	@ResponseStatus(value = HttpStatus.OK)
	@PostMapping
	public AutenticacionBiometricaResponse registrarDatosBiometricos(@RequestPart  MultipartFile fotografia){
		return autenticacionService.autenticar(fotografia);
	}
}
