package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.administracion.usuario.beans;

import java.io.Serializable;
import java.math.BigInteger;

import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.administracion.usuario.model.Usuario;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class AutenticacionBasicaResponse implements Serializable{

	private static final long serialVersionUID = -8374305449469163735L;

	BigInteger id;
	String usuario;
	String nombre;
	String apellido;
	BigInteger idPerfil;
	String nombrePerfil;
	

	public AutenticacionBasicaResponse(Usuario usuarioAutenticado) {
		this.id = usuarioAutenticado.getId();
		this.usuario = usuarioAutenticado.getUsuario();
		this.nombre = usuarioAutenticado.getNombre();
		this.apellido = usuarioAutenticado.getApellidos();
	}
}
