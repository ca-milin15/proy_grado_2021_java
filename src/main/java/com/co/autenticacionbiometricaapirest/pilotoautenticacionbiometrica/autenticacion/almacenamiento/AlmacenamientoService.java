package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.almacenamiento;

public interface AlmacenamientoService {

	String cargarObjeto(String fotografiaBase64);
	String descargarObjeto(String nombreRepositorioS3, String nombreArchivo);
	
}
