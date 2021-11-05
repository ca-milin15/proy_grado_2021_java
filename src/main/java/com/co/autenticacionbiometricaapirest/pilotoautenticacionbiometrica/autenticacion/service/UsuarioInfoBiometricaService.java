package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.service;

import java.math.BigInteger;

import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.model.UsuarioInfoBiometrica;

public interface UsuarioInfoBiometricaService {

	UsuarioInfoBiometrica almacenarEntidad(UsuarioInfoBiometrica usuarioInfoBiometrica);
	
	UsuarioInfoBiometrica buscarInfoBiometricaPorUsuario(BigInteger idUsuario);
}
