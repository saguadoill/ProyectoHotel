package com.capgemini.services.impls;

import java.util.List;

import com.capgemini.dtos.ReservaDTO;

public interface IReservaService {
	
	List<ReservaDTO> findAll();
	
	ReservaDTO findById(int id);
	
	boolean addReserva(ReservaDTO reservaDTO);
	
	boolean updateReserva(ReservaDTO reservaDTO);
	
	boolean deleteReserva(int id);
}
