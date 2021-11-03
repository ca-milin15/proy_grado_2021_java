package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.administracion.usuario.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Setter
@Getter
@Table(name = "resource")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Recurso extends EntidadGeneral{

	private static final long serialVersionUID = 7997967859079481378L;

	String name;
	String pathResource;
	
	@ManyToOne( fetch = FetchType.LAZY)
	Perfil perfil;
}
