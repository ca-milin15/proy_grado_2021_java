package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.utilidades.exception;

public class ArchivoProcesoDescargaRuntimeException extends RuntimeException{

	private static final long serialVersionUID = 1103356855920274707L;

	public ArchivoProcesoDescargaRuntimeException() {
		super();
	}

	public ArchivoProcesoDescargaRuntimeException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ArchivoProcesoDescargaRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public ArchivoProcesoDescargaRuntimeException(String message) {
		super(message);
	}

	public ArchivoProcesoDescargaRuntimeException(Throwable cause) {
		super(cause);
	}

	
}
