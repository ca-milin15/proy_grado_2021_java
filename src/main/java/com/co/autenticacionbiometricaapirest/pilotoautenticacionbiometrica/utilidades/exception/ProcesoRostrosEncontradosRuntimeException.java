package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.utilidades.exception;

public class ProcesoRostrosEncontradosRuntimeException extends RuntimeException{

	private static final long serialVersionUID = -5375832930449283920L;

	public ProcesoRostrosEncontradosRuntimeException() {
		super();
	}

	public ProcesoRostrosEncontradosRuntimeException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ProcesoRostrosEncontradosRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProcesoRostrosEncontradosRuntimeException(String message) {
		super(message);
	}

	public ProcesoRostrosEncontradosRuntimeException(Throwable cause) {
		super(cause);
	}

	
}
