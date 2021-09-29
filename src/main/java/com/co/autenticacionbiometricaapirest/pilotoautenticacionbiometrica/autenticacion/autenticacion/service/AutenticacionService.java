package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.autenticacion.service;

import org.springframework.web.multipart.MultipartFile;

import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.autenticacion.beans.AutenticacionBiometricaResponse;

public interface AutenticacionService {

	AutenticacionBiometricaResponse autenticar (MultipartFile MultipartFile);
}
