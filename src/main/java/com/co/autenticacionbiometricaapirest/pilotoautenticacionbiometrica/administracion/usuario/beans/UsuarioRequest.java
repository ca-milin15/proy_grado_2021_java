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
public class UsuarioRequest implements Serializable{

	private static final long serialVersionUID = 1125950590476271029L;

	String usuario;
	String clave;
	
}
