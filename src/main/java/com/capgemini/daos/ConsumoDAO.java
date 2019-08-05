package com.capgemini.daos;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.capgemini.entities.ConsumoEntity;

@Mapper
public interface ConsumoDAO {
	
	List<ConsumoEntity> findAll();

	ConsumoEntity findById(int id);

	boolean addConsumo(ConsumoEntity consumo);

	boolean updateConsumo(ConsumoEntity consumo);

	boolean deleteConsumo(int id);
}
