package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.administracion.usuario.model.EntidadGeneral;
import com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.administracion.usuario.model.Usuario;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Setter
@Getter
@Table(name = "user_info_biometric")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UsuarioInfoBiometrica  extends EntidadGeneral{

	private static final long serialVersionUID = -8172577303672087596L;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idUser")
	Usuario usuario;
	
	@Column(name = "faceId")
	String idRostro;
	
	@Column(name = "externalId")
	String idExternal;
	
	@Column(name = "bucketS3name")
	String rutaFoto;
	
	@Column(name = "fileName")
	String nombreFotografia;
}
