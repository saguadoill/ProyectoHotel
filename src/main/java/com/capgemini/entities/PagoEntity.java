package com.capgemini.entities;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PagoEntity {
		
	private int id;
	private int idReserva;
	private float total;
	private LocalDate fechaPago;
	
}
