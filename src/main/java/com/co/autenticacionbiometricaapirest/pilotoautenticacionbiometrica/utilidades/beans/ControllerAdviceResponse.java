package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.utilidades.beans;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class ControllerAdviceResponse implements Serializable{

	private static final long serialVersionUID = -9039495605094768670L;
	
	String mensaje;
	String causa;
}
