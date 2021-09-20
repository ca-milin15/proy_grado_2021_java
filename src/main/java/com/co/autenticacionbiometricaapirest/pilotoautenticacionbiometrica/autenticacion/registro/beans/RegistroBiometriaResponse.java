package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.registro.beans;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegistroBiometriaResponse implements Serializable{

	private static final long serialVersionUID = 5957629930734893593L;

	String rostroId;
	String externalId;
	String fotografia;
}
