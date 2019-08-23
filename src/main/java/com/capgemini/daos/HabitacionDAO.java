package com.capgemini.daos;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.capgemini.entities.HabitacionEntity;

@Mapper
public interface HabitacionDAO {

	List<HabitacionEntity> findAll();
	
	List<HabitacionEntity> findAllHabitacionesByHotelId(int id);
	
	List<HabitacionEntity> findHabitacionesDisponiblesHotel(int idHotel, @Param("fechaInicio") LocalDate fechaInicio, @Param("fechaFin") LocalDate fechaFin);
	
	HabitacionEntity findHabitacionById(int id);
	
	boolean addHabitacion(HabitacionEntity habitacion);
	
	boolean updateHabitacion(HabitacionEntity habitacion);
	
	boolean deleteHabitacion(int id);
	
}
