package com.capgemini.entities;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ReservaEntity {
	
	private int id;
	private int idCliente;
	private int idHabitacion;
	private LocalDate fechaReserva;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private Float costeAlojamiento;
	private String estado;
	

}
