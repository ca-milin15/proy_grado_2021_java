package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.utilidades.exception;

public class UsuarioNoEncontradoRuntimeException extends RuntimeException{

	private static final long serialVersionUID = 3969061159376300594L;

	public UsuarioNoEncontradoRuntimeException() {
		super();
	}

	public UsuarioNoEncontradoRuntimeException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UsuarioNoEncontradoRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public UsuarioNoEncontradoRuntimeException(String message) {
		super(message);
	}

	public UsuarioNoEncontradoRuntimeException(Throwable cause) {
		super(cause);
	}

}
