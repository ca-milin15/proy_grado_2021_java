package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.autenticacion.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.autenticacion.service.AutenticacionService;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.utilidades.enums.MessageStaticClass;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.utilidades.exception.ArchivoProcesoDescargaRuntimeException;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("autenticacion")
public class AutenticacionController {

	AutenticacionService autenticacionService;

	@PostMapping
	public ResponseEntity<InputStreamResource> registrarDatosBiometricos(@RequestPart MultipartFile fotografia) {
		try {
			var pathFile = autenticacionService.autenticar(fotografia);
			var file = pathFile.toFile();
			return ResponseEntity.ok().contentLength(file.length()).contentType(MediaType.APPLICATION_OCTET_STREAM)
					.body(new InputStreamResource(new FileInputStream(file)));
		} catch (FileNotFoundException e) {
			log.error(ExceptionUtils.getMessage(e));
			throw new ArchivoProcesoDescargaRuntimeException(MessageStaticClass.ERR_CONST_ARCHIVO_DESCARGA.getMensaje());
		}
	}
}
