package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.administracion.usuario.beans;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class AutenticacionBasicaRequest implements Serializable{

	private static final long serialVersionUID = -449250944151246649L;

	String usuario;
	String clave;
}
