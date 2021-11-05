package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.model.UsuarioInfoBiometrica;

@Repository
public interface UsuarioInfoBiometricaRepository extends JpaRepository<UsuarioInfoBiometrica, BigInteger>{

	UsuarioInfoBiometrica findByUsuarioId(BigInteger idUsuario);
}
