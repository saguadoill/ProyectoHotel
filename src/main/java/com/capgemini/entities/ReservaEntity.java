package com.capgemini.entities;

import java.time.LocalDate;

import com.capgemini.utils.LocalDateDeserializer;
import com.capgemini.utils.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

@Data
public class ReservaEntity {
	
	private int id;
	private int idCliente;
	private int idHabitacion;
	
	@JsonDeserialize(using = LocalDateDeserializer.class)  
	@JsonSerialize(using = LocalDateSerializer.class) 
	private LocalDate fechaReserva;
	
	@JsonDeserialize(using = LocalDateDeserializer.class)  
	@JsonSerialize(using = LocalDateSerializer.class) 
	private LocalDate fechaInicio;
	
	@JsonDeserialize(using = LocalDateDeserializer.class)  
	@JsonSerialize(using = LocalDateSerializer.class) 
	private LocalDate fechaFin;
	private Float costeAlojamiento;
	private String estado;	

}
