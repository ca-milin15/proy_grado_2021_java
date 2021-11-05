package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.almacenamiento;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Service;

import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.config.AWSPropiedadesSistema;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.utilidades.enums.MessageStaticClass;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.transfer.s3.S3ClientConfiguration;
import software.amazon.awssdk.transfer.s3.S3TransferManager;

@Slf4j
@Service
@AllArgsConstructor
public class S3AlmacenamientoServiceImpl implements AlmacenamientoService {

	ObjectMapper objectMapper;
	AWSPropiedadesSistema propiedadesSistema;
	
	@Override
	public String cargarObjeto(String bucketName, String fileName, File fotografia) {
		try {
			var awsCredentials = AwsBasicCredentials.create(
					propiedadesSistema.getConfig().getAk(), 
					propiedadesSistema.getConfig().getSk()
			);
			var s3ClientConfig = S3ClientConfiguration.builder()
						.region(Region.US_EAST_1)
						.credentialsProvider(StaticCredentialsProvider.create(awsCredentials)).build();
			var s3TransferManager = S3TransferManager.builder().s3ClientConfiguration(s3ClientConfig).build();
			s3TransferManager.upload(builder -> 
				builder.putObjectRequest(poq -> 
					poq.bucket(bucketName).key(fileName.concat(fotografia.getName()))).source(Path.of(fotografia.getPath())));
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
			var s3TransferManager = S3TransferManager.create();
			var download = s3TransferManager.download(builder -> 
				builder.getObjectRequest(poq -> 
					poq.bucket(nombreRepositorioS3).key(nombreArchivo)).destination(Paths.get(System.getProperty("java.io.tmpdir"), nombreArchivo)));
			log.info("descargarObjeto: ".concat(objectMapper.writeValueAsString(download.completionFuture().join().response())));
			return null;
		} catch (Exception e) {
			log.error(ExceptionUtils.getMessage(e));
			log.error(ExceptionUtils.getRootCauseMessage(e));
			throw new RuntimeException(MessageStaticClass.ERR_DESCARGA_ARCHIVO.getMensaje());
		}
	}



}
