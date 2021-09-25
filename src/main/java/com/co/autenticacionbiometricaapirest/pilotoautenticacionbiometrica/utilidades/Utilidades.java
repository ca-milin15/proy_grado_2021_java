package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.utilidades;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Utilidades {

	public File crearArchivoDesdeMultipart (MultipartFile multipart) {
		var pathFile = Paths.get(System.getProperty("java.io.tmpdir").concat(multipart.getOriginalFilename()));
		try {
			Files.copy(multipart.getInputStream(), pathFile);
			return pathFile.toFile();
		} catch (FileAlreadyExistsException e) {
			return pathFile.toFile();
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}
}
