package com.capgemini.services.impls;

import java.time.LocalDate;
import java.util.List;

import com.capgemini.dtos.HabitacionDTO;

public interface IHabitacionService {
	
	List<HabitacionDTO> findAll();
	
	List<HabitacionDTO> findAllHabitacionesByHotelId(int id);
	
	List<HabitacionDTO> findHabitacionesDisponiblesHotel(int idHotel, LocalDate fechaInicio, LocalDate fechaFin);
	
	HabitacionDTO findHabitacionById(int id);
	
	boolean addHabitacion(HabitacionDTO habitacion);
	
	boolean updateHabitacion(HabitacionDTO habitacion);
	
	boolean deleteHabitacion(int id);
	
	
}
