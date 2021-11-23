package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.autenticacion.service;

import org.springframework.web.multipart.MultipartFile;

import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.autenticacion.beans.AutenticacionDTO;

public interface AutenticacionService {

	AutenticacionDTO autenticar (MultipartFile MultipartFile);
}
