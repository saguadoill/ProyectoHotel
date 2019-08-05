package com.capgemini.daos;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.capgemini.entities.HabitacionEntity;

@Mapper
public interface HabitacionDAO {

	List<HabitacionEntity> findAll();
	
	HabitacionEntity findHabitacionById(int id);
	
	boolean addHabitacion(HabitacionEntity habitacion);
	
	boolean updateHabitacion(HabitacionEntity habitacion);
	
	boolean deleteHabitacion(int id);
	
}
