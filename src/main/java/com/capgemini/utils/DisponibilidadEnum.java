package com.capgemini.utils;

public enum DisponibilidadEnum {

	RESERVADA("reservada"),
	LIBRE("libre"),
	OCUPADA("ocupada"),
	FUERA_SERVICIO("fuera de servicio");
	
	private String disponible;


	private DisponibilidadEnum(String disponible) {
		this.disponible = disponible;
	}


	public String getDisponible() {
		return disponible;
	}
	
	
}
