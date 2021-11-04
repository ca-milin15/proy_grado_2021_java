package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.administracion.usuario.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.administracion.usuario.model.Usuario;

@Repository
public interface UsuarioServiceRepository extends JpaRepository<Usuario, BigInteger>{

	Usuario findByUsuarioAndClave(String usuario, String clave);
}
