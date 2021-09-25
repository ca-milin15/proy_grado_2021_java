package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "amazon-properties")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PropiedadesSistema {
	
	Registro registro;
	Autenticacion autenticacion;

	public static class Registro extends CommonAttributes{}
	
	public static class Autenticacion extends CommonAttributes{}

	@Getter
	@Setter
	@FieldDefaults(level = AccessLevel.PRIVATE)
	public static class CommonAttributes{
		String bucketName;
		String bucketFileContext;
	}
}
