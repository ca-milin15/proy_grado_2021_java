package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.utilidades.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public enum MessageStaticClass {

	ERR_CONSTRUCT_ARCHIVO ("Error en el servidor construyendo archivos."),
	ERR_CARGA_ARCHIVO ("Error en la carga del archivo al repositorio."),
	ERR_DESCARGA_ARCHIVO ("Error en la descarga del archivo del repositorio."),
	ERR_PROCESO_AUTENTICACION ("Ha ocurrido un error en el proceso de autenticacion."),
	ERR_CONSUMO_AUTENTICACION ("Ha ocurrido un error en el proceso en el sistema de autenticacion."),
	
	USUARIO_NO_ENCONTRADO ("El usuario no se encuentra registrado en el sistema."),

	ERR_USUARIO_INFO_BIOM_PERSISTENCIA ("Ha ocurrido un error almacenando la información biometrica del usuario.");
	
	String mensaje;

	private MessageStaticClass(String mensaje) {
		this.mensaje = mensaje;
	}
	
}
