package com.capgemini.daos;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.capgemini.entities.PagoEntity;

@Mapper
public interface PagoDAO {
	
	List<PagoEntity> findAll();
	
	PagoEntity findById(int id);
	
	boolean addPago(PagoEntity pago);
	
	boolean updatePago(PagoEntity pago);
	
	boolean deletePago(int id);
	
}
