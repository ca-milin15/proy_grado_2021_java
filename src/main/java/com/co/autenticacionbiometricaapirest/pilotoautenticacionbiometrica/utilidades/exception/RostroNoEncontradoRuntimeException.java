package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.utilidades.exception;

public class RostroNoEncontradoRuntimeException extends RuntimeException{

	private static final long serialVersionUID = -2538146632244382553L;

	public RostroNoEncontradoRuntimeException() {
		super();
	}

	public RostroNoEncontradoRuntimeException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RostroNoEncontradoRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public RostroNoEncontradoRuntimeException(String message) {
		super(message);
	}

	public RostroNoEncontradoRuntimeException(Throwable cause) {
		super(cause);
	}

	
}
