package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.registro.beans;

import java.io.Serializable;
import java.math.BigInteger;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegistroBiometriaRequest  implements Serializable{

	private static final long serialVersionUID = -1354653006485808470L;

	BigInteger idUsuario;
	String fotografiaBase64;

}
