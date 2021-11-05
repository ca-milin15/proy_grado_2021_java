package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.almacenamiento;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.config.AWSPropiedadesSistema;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.utilidades.enums.MessageStaticClass;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class S3AlmacenamientoServiceImpl implements AlmacenamientoService {

	ObjectMapper objectMapper;
	AWSPropiedadesSistema propiedadesSistema;
	
	@Override
	public String cargarObjeto(String bucketName, String fileName, File fotografia) {
		try {
			var awsCredentials = new BasicAWSCredentials(
					propiedadesSistema.getConfig().getAk(), 
					propiedadesSistema.getConfig().getSk()
			);
			var s3ClientConfig = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
						.withRegion(Regions.US_EAST_1).build();
			s3ClientConfig.putObject(bucketName, fileName.concat(fotografia.getName()), fotografia);
			return new StringBuilder().append(bucketName).append("/").append(fileName).append(fotografia.getName()).toString();
		} catch (Exception e) {
			log.error(ExceptionUtils.getMessage(e));
			log.error(ExceptionUtils.getRootCauseMessage(e));
			throw new RuntimeException(MessageStaticClass.ERR_CARGA_ARCHIVO.getMensaje());
		}
	}

	@Override
	public String descargarObjeto(String nombreRepositorioS3, String nombreArchivo) {
		try {
			var awsCredentials = new BasicAWSCredentials(
					propiedadesSistema.getConfig().getAk(), 
					propiedadesSistema.getConfig().getSk()
			);
			var s3ClientConfig = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
						.withRegion(Regions.US_EAST_1).build();
			var object = s3ClientConfig.getObject(nombreRepositorioS3, nombreArchivo);
			var pathFileToDownload = Paths.get(System.getProperty("java.io.tmpdir"), nombreArchivo);
			FileUtils.copyInputStreamToFile(object.getObjectContent(), pathFileToDownload.toFile());
			return pathFileToDownload.toString();
		} catch (Exception e) {
			log.error(ExceptionUtils.getMessage(e));
			log.error(ExceptionUtils.getRootCauseMessage(e));
			throw new RuntimeException(MessageStaticClass.ERR_DESCARGA_ARCHIVO.getMensaje());
		}
	}



}
