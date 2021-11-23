package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.administracion.usuario.beans;

import java.io.Serializable;
import java.math.BigInteger;

import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.administracion.usuario.model.Usuario;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class UsuarioResponse  implements Serializable{

	private static final long serialVersionUID = -5145934024185907079L;

	String nombreCompleto;
	
	BigInteger id;
	String usuario;
	String nombre;
	String apellido;
	BigInteger idPerfil;
	String nombrePerfil;
	
	public UsuarioResponse(Usuario usuario) {
		this.nombreCompleto = usuario.getNombre().concat(" ").concat(usuario.getApellidos());
	}
}
