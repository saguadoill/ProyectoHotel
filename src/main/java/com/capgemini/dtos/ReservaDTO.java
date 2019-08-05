package com.capgemini.dtos;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ReservaDTO {
		
	private int id;
	private ClienteDTO clienteDTO;
	private HabitacionDTO habitacionDTO;
	private LocalDate fechaReserva;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private Float costeAlojamiento;
	private String estado;
	
}
