package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.almacenamiento;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.transfer.s3.*;

@Slf4j
@Service
@AllArgsConstructor
public class S3AlmacenamientoServiceImpl implements AlmacenamientoService {

	ObjectMapper objectMapper;
	
	@Override
	public String cargarObjeto(String bucketName, String fileName, File fotografia) {
		try {
			var s3TransferManager = S3TransferManager.create();
			var upload = s3TransferManager.upload(builder -> 
				builder.putObjectRequest(poq -> 
					poq.bucket(bucketName).key(fileName)).source(Path.of(fotografia.getPath())));
			log.info("cargarObjeto: ".concat(objectMapper.writeValueAsString(upload.completionFuture().join().response())));
			return null;
		} catch (JsonProcessingException e) {
			log.error(e.getOriginalMessage());
			throw new RuntimeException();
		}
	}

	@Override
	public String descargarObjeto(String nombreRepositorioS3, String nombreArchivo) {
		try {
			var s3TransferManager = S3TransferManager.create();
			var download = s3TransferManager.download(builder -> 
				builder.getObjectRequest(poq -> 
					poq.bucket(nombreRepositorioS3).key(nombreArchivo)).destination(Paths.get("downloadedFile.txt")));
			log.info("descargarObjeto: ".concat(objectMapper.writeValueAsString(download.completionFuture().join().response())));
			return null;
		} catch (JsonProcessingException e) {
			log.error(e.getOriginalMessage());
			throw new RuntimeException();
		}
	}



}
