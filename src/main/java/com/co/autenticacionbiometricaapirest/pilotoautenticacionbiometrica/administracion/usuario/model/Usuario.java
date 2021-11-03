package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.administracion.usuario.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.model.UsuarioInfoBiometrica;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Setter
@Getter
@Table(name = "user")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Usuario extends EntidadGeneral{

	private static final long serialVersionUID = -2205194971565652858L;

	@Column(name = "username")
	String usuario;

	@Column(name = "password")
	String clave;
	
	@Column(name = "name")
	String nombre;
	
	@Column(name = "lastName")
	String apellidos;
	
	@Column(name = "identification")
	String identificacion;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idProfile")
	Perfil perfil;

	@OneToOne(mappedBy = "usuario", fetch = FetchType.LAZY)
	UsuarioInfoBiometrica usuarioInfoBiometrica;
}
