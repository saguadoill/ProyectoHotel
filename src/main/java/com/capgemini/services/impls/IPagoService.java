package com.capgemini.services.impls;

import java.util.List;

import com.capgemini.dtos.PagoDTO;

public interface IPagoService {
	
	List<PagoDTO> findAll();
	
	PagoDTO findById(int id);
	
	boolean addPago(PagoDTO hotelDTO);
	
	boolean updatePago(PagoDTO hotelDTO);
	
	boolean deletePago(int id);
	
}
