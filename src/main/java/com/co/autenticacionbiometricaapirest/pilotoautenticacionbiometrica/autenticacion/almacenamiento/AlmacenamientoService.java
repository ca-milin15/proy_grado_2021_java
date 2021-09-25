package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.almacenamiento;

import java.io.File;

public interface AlmacenamientoService {

	String cargarObjeto(String bucketName, String fileName, File fotografia);
	String descargarObjeto(String nombreRepositorioS3, String nombreArchivo);
	
}
