package com.co.autenticacionbiometricaapirest.pilotoautenticacionbiometrica.autenticacion.autenticacion.beans;

import java.io.Serializable;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AutenticacionBiometricaAWSResponse implements Serializable{
	
	private static final long serialVersionUID = -910374792507756696L;
	
	int statusCode;
	Body body;
	
	@Getter
	@Setter
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Body {
		List<FaceMatch> faceMatches;
		
		@Getter
		@Setter
		@Builder
		@AllArgsConstructor
		@NoArgsConstructor
		public static class FaceMatch implements Serializable {
			
			private static final long serialVersionUID = 8238452027870021720L;
			
			Integer similarity;
			Face face;
			
			@Getter
			@Setter
			@Builder
			@AllArgsConstructor
			@NoArgsConstructor
			public static class Face implements Serializable{
				
				private static final long serialVersionUID = -9155284751707541968L;
				
				String faceId;
				String imageId;
				String externalImageId;
				Float confidence;
			}
		}
	}
}
