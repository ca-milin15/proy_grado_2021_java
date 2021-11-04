package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.service;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Service;

import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.model.UsuarioInfoBiometrica;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.repository.UsuarioInfoBiometricaRepository;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.utilidades.enums.MessageStaticClass;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.utilidades.exception.UsuarioInfoBiometricaPersistenciaRuntimeException;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class UsuarioInfoBiometricaServiceImpl implements UsuarioInfoBiometricaService{

	UsuarioInfoBiometricaRepository usuarioInfoBiometricaRepository;
	
	@Override
	public UsuarioInfoBiometrica almacenarEntidad(UsuarioInfoBiometrica usuarioInfoBiometrica) {
		try {
			return usuarioInfoBiometricaRepository.save(usuarioInfoBiometrica);
		} catch (Exception e) {
			log.error(ExceptionUtils.getRootCauseMessage(e));
			throw new UsuarioInfoBiometricaPersistenciaRuntimeException(MessageStaticClass.ERR_USUARIO_INFO_BIOM_PERSISTENCIA.getMensaje());
		}
	}

}
