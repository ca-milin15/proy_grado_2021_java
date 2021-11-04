package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.administracion.usuario.service;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.administracion.usuario.beans.AutenticacionBasicaRequest;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.administracion.usuario.beans.AutenticacionBasicaResponse;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.administracion.usuario.beans.UsuarioRequest;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.administracion.usuario.beans.UsuarioResponse;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.administracion.usuario.repository.UsuarioServiceRepository;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.utilidades.enums.MessageStaticClass;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.utilidades.exception.UsuarioNoEncontradoRuntimeException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService{

	UsuarioServiceRepository usuarioServiceRepository;
	
	@Override
	public AutenticacionBasicaResponse autenticacionBasica(AutenticacionBasicaRequest autenticacionBasicaRequest) {
		
		var usuarioAutenticado = usuarioServiceRepository.findByUsuarioAndClave(autenticacionBasicaRequest.getUsuario(), autenticacionBasicaRequest.getClave());
		if(!ObjectUtils.isEmpty(usuarioAutenticado)) {
			return new AutenticacionBasicaResponse(usuarioAutenticado);
		} else {
			throw new UsuarioNoEncontradoRuntimeException(MessageStaticClass.USUARIO_NO_ENCONTRADO.getMensaje());
		}
	}
	
	@Override
	public UsuarioResponse crearUsuario(UsuarioRequest usuarioRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UsuarioResponse actualizarUsuario(UsuarioRequest usuarioRequest) {
		// TODO Auto-generated method stub
		return null;
	}


}
