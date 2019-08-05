package com.capgemini.dtos;

import lombok.Data;

@Data
public class ClienteDTO {
	
	private int cli_id;
	private String cli_dni;
	private String cli_nombre;
	private String cli_apellido;
	private String cli_email;
	private String cli_direccion;
	private String cli_codigopostal;
	private String cli_ciudad;
	private int cli_puntos_dto;
	
	//private List<ConsumoDTO> consumo;
	
}
