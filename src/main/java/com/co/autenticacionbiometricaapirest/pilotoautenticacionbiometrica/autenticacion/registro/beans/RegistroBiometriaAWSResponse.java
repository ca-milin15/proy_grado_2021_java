package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.registro.beans;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegistroBiometriaAWSResponse implements Serializable{

	private static final long serialVersionUID = 5516156732346093125L;

	int statusCode;
	Body body;
	
	@Getter
	@Setter
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Body {
		String faceId;
	}
}
