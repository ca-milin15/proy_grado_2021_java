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

	ERR_USUARIO_INFO_BIOM_PERSISTENCIA ("Ha ocurrido un error almacenando la información biometrica del usuario."),
	ERR_USUARIO_INFO_BIOM_CONSULTA ("Ha ocurrido un error consultando la información biometrica del usuario."),
	ERR_USUARIO_INFO_BIOM_EXISTENTE ("El usuario ya tiene datos biometricos registrados en el sistema."),

	ERR_ROSTRO_NO_ENCONTRADO ("No se ha encontrado el rostro del usuario en el sistema."),
	ERR_ROSTRO_NO_CUMPLE_REQ ("Se han encontrado posibles rostros correspondientes al usuario pero no cumplen con los requisitos."),
	
	ERR_PROCESO_ROSTROS_ENCON ("Se ha producido un error en el procesamiento de los rostros encontrados."),
	
	ERR_CONST_ARCHIVO_DESCARGA ("Ha ocurrido un error en la descarga de la imagen.");
	
	String mensaje;

	private MessageStaticClass(String mensaje) {
		this.mensaje = mensaje;
	}
	
}
