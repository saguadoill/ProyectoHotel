package com.capgemini.entities;

import lombok.Data;

@Data
public class ClienteEntity {

	private int id;
	private String dni;
	private String nombre;
	private String apellido;
	private String email;
	private String direccion;
	private String codigoPostal;
	private String ciudad;
	private String password;
	private int puntosDescuento;
		
}
