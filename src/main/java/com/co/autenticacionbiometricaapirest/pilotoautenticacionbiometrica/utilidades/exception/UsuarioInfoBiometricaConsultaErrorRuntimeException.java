package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.utilidades.exception;

public class UsuarioInfoBiometricaConsultaErrorRuntimeException extends RuntimeException{

	private static final long serialVersionUID = -3940607006591161242L;

	public UsuarioInfoBiometricaConsultaErrorRuntimeException() {
		super();
	}

	public UsuarioInfoBiometricaConsultaErrorRuntimeException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UsuarioInfoBiometricaConsultaErrorRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public UsuarioInfoBiometricaConsultaErrorRuntimeException(String message) {
		super(message);
	}

	public UsuarioInfoBiometricaConsultaErrorRuntimeException(Throwable cause) {
		super(cause);
	}

	
}
