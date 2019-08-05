package com.capgemini.entities;

import lombok.Data;

@Data
public class ConsumoEntity {
	
	private int id;
	private int idReserva;
	private int idProducto;
	private int cantidad;
	private float precio;
}
