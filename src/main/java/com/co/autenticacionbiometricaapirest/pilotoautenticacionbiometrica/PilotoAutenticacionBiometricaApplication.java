package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@SpringBootApplication
@EnableEncryptableProperties
public class PilotoAutenticacionBiometricaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PilotoAutenticacionBiometricaApplication.class, args);
	}

}
