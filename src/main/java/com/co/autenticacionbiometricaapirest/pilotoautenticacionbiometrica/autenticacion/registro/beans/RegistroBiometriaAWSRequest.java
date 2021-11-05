package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.registro.beans;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegistroBiometriaAWSRequest implements Serializable{

	private static final long serialVersionUID = 5516156732346093125L;

	String operationType;
	ObjectRequest objectRequest;
	
	@Getter
	@Setter
	@Builder
	public static class ObjectRequest {
		String imageS3Bucket;
		String imageBucketName;
		String externalId;
	}
}
