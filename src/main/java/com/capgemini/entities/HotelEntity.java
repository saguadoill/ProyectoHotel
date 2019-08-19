package com.capgemini.entities;

import lombok.Data;

@Data
public class HotelEntity {
	
	private int id;
	private String nombre;
	private String categoria;
	private String zona;
	private String direccion;
	private String imagenUrl;
	
}
