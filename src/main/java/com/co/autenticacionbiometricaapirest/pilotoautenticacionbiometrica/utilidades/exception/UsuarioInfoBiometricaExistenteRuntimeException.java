package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.utilidades.exception;

public class UsuarioInfoBiometricaExistenteRuntimeException extends RuntimeException{

	private static final long serialVersionUID = 2576090513306987069L;

	public UsuarioInfoBiometricaExistenteRuntimeException() {
		super();
	}

	public UsuarioInfoBiometricaExistenteRuntimeException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UsuarioInfoBiometricaExistenteRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public UsuarioInfoBiometricaExistenteRuntimeException(String message) {
		super(message);
	}

	public UsuarioInfoBiometricaExistenteRuntimeException(Throwable cause) {
		super(cause);
	}

	
}
