package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.registro.service;

import java.math.BigInteger;

import org.springframework.web.multipart.MultipartFile;

import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.registro.beans.RegistroBiometriaResponse;

public interface RegistroService {

	RegistroBiometriaResponse registrarDatosBiometricos(MultipartFile multipartFileFotografia, BigInteger idUsuario);
}
