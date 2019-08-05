package com.capgemini.services.impls;

import java.util.List;

import com.capgemini.dtos.ConsumoDTO;

public interface IConsumoService {

	List<ConsumoDTO> findAll();

	ConsumoDTO findById(int id);

	boolean addConsumo(ConsumoDTO consumo);

	boolean updateConsumo(ConsumoDTO consumo);

	boolean deleteConsumo(int id);

}
