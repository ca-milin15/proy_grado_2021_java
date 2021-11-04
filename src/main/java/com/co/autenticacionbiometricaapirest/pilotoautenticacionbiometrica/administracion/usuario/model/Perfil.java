package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.administracion.usuario.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Setter
@Getter
@Table(name = "profile")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Perfil extends EntidadGeneral {


	private static final long serialVersionUID = -6058065483968908441L;

	@Column(name = "name")
	String nombre;

	@OneToOne(mappedBy = "perfil", fetch = FetchType.LAZY)
	Usuario usuario;
	
	@OneToMany
	@JoinColumn(name = "id_resource")
	List<Recurso> recursos;
}
