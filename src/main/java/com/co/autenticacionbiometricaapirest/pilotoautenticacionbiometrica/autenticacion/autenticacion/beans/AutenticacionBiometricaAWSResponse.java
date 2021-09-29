package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.autenticacion.beans;

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
public class AutenticacionBiometricaAWSResponse implements Serializable{
	
	private static final long serialVersionUID = -910374792507756696L;
	
	int statusCode;
	Body body;
	
	@Getter
	@Setter
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Body {
		String imageId;
	}
}
