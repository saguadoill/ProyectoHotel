package com.capgemini.services.impls;

import java.util.List;

import com.capgemini.dtos.HabitacionDTO;

public interface IHabitacionService {
	
	List<HabitacionDTO> findAll();
	
	HabitacionDTO findHabitacionById(int id);
	
	boolean addHabitacion(HabitacionDTO habitacion);
	
	boolean updateHabitacion(HabitacionDTO habitacion);
	
	boolean deleteHabitacion(int id);
}
