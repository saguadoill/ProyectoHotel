package com.capgemini.entities;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PagoEntity {
		
	private int id;
	private int re_id;
	private float total;
	private LocalDate fecha_pago;
	
}
