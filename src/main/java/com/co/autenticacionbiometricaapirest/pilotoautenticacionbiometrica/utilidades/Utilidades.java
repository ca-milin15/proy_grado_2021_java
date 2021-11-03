package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.utilidades;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.utilidades.enums.MessageStaticClass;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Utilidades {

	
	public File crearArchivoDesdeMultipart (MultipartFile multipart) {
		var pathFile = Paths.get(System.getProperty("java.io.tmpdir"), multipart.getOriginalFilename());
		log.info("crearArchivoDesdeMultipart: ".concat(pathFile.toString()));
		try {
			Files.copy(multipart.getInputStream(), pathFile);
			return pathFile.toFile();
		} catch (FileAlreadyExistsException e) {
			return pathFile.toFile();
		} catch (IOException e) {
			log.error(e.getMessage());
			throw new RuntimeException(MessageStaticClass.ERR_CONSTRUCT_ARCHIVO.getMensaje());
		}
	}



}
