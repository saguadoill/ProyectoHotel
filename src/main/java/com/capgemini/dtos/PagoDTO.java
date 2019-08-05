package com.capgemini.dtos;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PagoDTO {
	
	private int id;
	private int re_id;
	private float total;
	private LocalDate fecha_pago;
	
}
