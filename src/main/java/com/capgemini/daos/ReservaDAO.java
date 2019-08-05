package com.capgemini.daos;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.capgemini.entities.ReservaEntity;

@Mapper
public interface ReservaDAO {
	
	List<ReservaEntity> findAll();
	
	ReservaEntity findById(int id);
	
	boolean addReserva(ReservaEntity reserva);
	
	boolean updateReserva(ReservaEntity reserva);
	
	boolean deleteReserva(int id);
	
}
