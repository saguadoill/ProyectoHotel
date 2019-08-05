package com.capgemini.utils;

public enum EstadoReservaEnum {
	
	EN_PROCESO("procesada"),
	CONFIRMADA("confirmada"),
	RESERVADA("reservada"),
	FINALIZADA("finalizada");
	
	private String estado;

	private EstadoReservaEnum(String estado) {
		this.estado = estado;
	}



	public String getEstado() {
		return estado;
	}

}
