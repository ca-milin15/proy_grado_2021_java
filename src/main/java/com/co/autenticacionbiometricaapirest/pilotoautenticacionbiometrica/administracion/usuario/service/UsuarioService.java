package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.administracion.usuario.service;

import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.administracion.usuario.beans.UsuarioRequest;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.administracion.usuario.beans.UsuarioResponse;

public interface UsuarioService {

	UsuarioResponse crearUsuario(UsuarioRequest usuarioRequest);
	UsuarioResponse actualizarUsuario(UsuarioRequest usuarioRequest);
}
