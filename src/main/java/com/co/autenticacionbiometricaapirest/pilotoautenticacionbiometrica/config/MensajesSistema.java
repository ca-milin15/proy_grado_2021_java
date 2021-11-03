package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "mensajes-sistema")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MensajesSistema {

	Archivo archivo;

	@Getter
	@Setter
	@FieldDefaults(level = AccessLevel.PRIVATE)
	public static class Archivo {
		String errorCreacionArchivoEnServidor;
	}
}
