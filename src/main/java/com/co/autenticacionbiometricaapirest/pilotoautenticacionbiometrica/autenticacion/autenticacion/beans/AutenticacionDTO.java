package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.autenticacion.beans;

import java.io.Serializable;
import java.nio.file.Path;

import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.administracion.usuario.beans.UsuarioResponse;

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
public class AutenticacionDTO implements Serializable{
	
	private static final long serialVersionUID = 6807964690449195813L;

	Path archivoAutenticacion;
	UsuarioResponse usuarioResponse;
}
