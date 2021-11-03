package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.administracion.usuario.model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@MappedSuperclass
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EntidadGeneral implements Serializable{

	private static final long serialVersionUID = 9093876223771989815L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	BigInteger id;
	
}
