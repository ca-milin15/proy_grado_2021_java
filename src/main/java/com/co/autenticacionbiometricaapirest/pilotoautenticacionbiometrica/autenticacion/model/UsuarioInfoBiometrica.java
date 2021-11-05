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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Setter
@Getter
@Builder
@Table(name = "user_info_biometric")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioInfoBiometrica  extends EntidadGeneral{

	private static final long serialVersionUID = -8172577303672087596L;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_user")
	Usuario usuario;
	
	@Column(name = "face_id")
	String idRostro;
	
	@Column(name = "external_id")
	String idExternal;
	
	@Column(name = "bucket_name")
	String rutaFoto;
	
	@Column(name = "file_name")
	String nombreFotografia;
}
