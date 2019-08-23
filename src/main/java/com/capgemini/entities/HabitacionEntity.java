package com.capgemini.entities;

import lombok.Data;

@Data
public class HabitacionEntity {
	
	private int id;
	private int piso;
	private int numero;
	private String vista;
	private String clase;
	private int camas;
	private float precio;
	private int personas;
	private String estado;
	private int idHotel;
	private String imagenUrl;
	
	
}