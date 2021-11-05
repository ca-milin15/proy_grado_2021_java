package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.almacenamiento;

import java.io.File;
import java.nio.file.Path;

public interface AlmacenamientoService {

	String cargarObjeto(String bucketName, String fileName, File fotografia);
	Path descargarObjeto(String nombreRepositorioS3, String nombreArchivo);
	
}
