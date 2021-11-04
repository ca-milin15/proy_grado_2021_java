package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.administracion.usuario.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.administracion.usuario.beans.AutenticacionBasicaRequest;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.administracion.usuario.beans.AutenticacionBasicaResponse;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.administracion.usuario.beans.UsuarioRequest;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.administracion.usuario.beans.UsuarioResponse;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.administracion.usuario.service.UsuarioService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("usuario")
public class UsuarioController {

	UsuarioService usuarioService;

	@PostMapping
	@ResponseStatus(value = HttpStatus.OK)
	public UsuarioResponse registrarDatosBiometricos(@RequestBody UsuarioRequest usuarioRequest){
		return usuarioService.crearUsuario(usuarioRequest);
	}

	@PutMapping
	@ResponseStatus(value = HttpStatus.OK)
	public UsuarioResponse actualizarDatosBiometricos(@RequestBody UsuarioRequest usuarioRequest){
		return usuarioService.actualizarUsuario(usuarioRequest);
	}
	

	@PostMapping("autenticacion-basica")
	@ResponseStatus(value = HttpStatus.OK)
	public AutenticacionBasicaResponse autenticacionBasica(@RequestBody AutenticacionBasicaRequest autenticacionBasicaRequest){
		return usuarioService.autenticacionBasica(autenticacionBasicaRequest);
	}
}
