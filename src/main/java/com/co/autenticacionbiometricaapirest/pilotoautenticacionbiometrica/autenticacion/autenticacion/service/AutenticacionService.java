package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.autenticacion.service;

import java.nio.file.Path;

import org.springframework.web.multipart.MultipartFile;

public interface AutenticacionService {

	Path autenticar (MultipartFile MultipartFile);
}
