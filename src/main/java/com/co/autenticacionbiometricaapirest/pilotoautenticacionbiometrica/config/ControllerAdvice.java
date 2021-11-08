package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.config;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.utilidades.beans.ControllerAdviceResponse;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.utilidades.exception.RostroNoEncontradoRuntimeException;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.utilidades.exception.UsuarioNoEncontradoRuntimeException;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UsuarioNoEncontradoRuntimeException.class)
	public ResponseEntity<ControllerAdviceResponse> userNotFoundRuntimeException(RuntimeException ex, WebRequest request){
		var objetoRespuesta = new ControllerAdviceResponse(ex.getMessage(), ExceptionUtils.getMessage(ex));
		return new ResponseEntity<>(objetoRespuesta, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(RostroNoEncontradoRuntimeException.class)
	public ResponseEntity<ControllerAdviceResponse> rostroNoEncontradoRuntimeException(RuntimeException ex, WebRequest request){
		var objetoRespuesta = new ControllerAdviceResponse(ex.getMessage(), ExceptionUtils.getMessage(ex));
		return new ResponseEntity<>(objetoRespuesta, HttpStatus.CONFLICT);
	}
	
}
