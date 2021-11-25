package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.utilidades.exception;

public class UsuarioDuplicadoRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 7755292360501287281L;

	public UsuarioDuplicadoRuntimeException() {
		super();
	}

	public UsuarioDuplicadoRuntimeException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UsuarioDuplicadoRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public UsuarioDuplicadoRuntimeException(String message) {
		super(message);
	}

	public UsuarioDuplicadoRuntimeException(Throwable cause) {
		super(cause);
	}

	
}
