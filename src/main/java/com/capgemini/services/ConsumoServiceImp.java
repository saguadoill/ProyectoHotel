package com.capgemini.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.daos.ConsumoDAO;
import com.capgemini.dtos.ConsumoDTO;
import com.capgemini.entities.ConsumoEntity;
import com.capgemini.services.impls.IConsumoService;
import com.capgemini.services.impls.IProductoService;
import com.capgemini.services.impls.IReservaService;

@Service
public class ConsumoServiceImp implements IConsumoService {

	@Autowired
	ConsumoDAO consumoDAO;

	@Autowired
	IReservaService reservasService;

	@Autowired
	IProductoService productoService;

	@Override
	public List<ConsumoDTO> findAll() {
		List<ConsumoEntity> listaConsumoEntity = consumoDAO.findAll();
		List<ConsumoDTO> listaConsumosDto = new ArrayList<ConsumoDTO>();

		for (ConsumoEntity consumo : listaConsumoEntity) {
			ConsumoDTO consumoDTO = new ConsumoDTO();
			consumoDTO.setId(consumo.getId());
			consumoDTO.setReserva(reservasService.findById(consumo.getIdReserva()));
			consumoDTO.setProducto(productoService.findProductoById(consumo.getIdProducto()));
			consumoDTO.setCantidad(consumo.getCantidad());
			consumoDTO.setPrecio(consumo.getPrecio());
			listaConsumosDto.add(consumoDTO);
		}

		return listaConsumosDto;
	}

	@Override
	public ConsumoDTO findById(int id) {

		ConsumoDTO consumoDTO = new ConsumoDTO();
		ConsumoEntity consumoEntity = consumoDAO.findById(id);

		consumoDTO.setId(consumoEntity.getId());
		consumoDTO.setReserva(reservasService.findById(consumoEntity.getIdReserva()));
		consumoDTO.setProducto(productoService.findProductoById(consumoEntity.getIdProducto()));
		consumoDTO.setCantidad(consumoEntity.getCantidad());
		consumoDTO.setPrecio(consumoEntity.getPrecio());

		return consumoDTO;
	}

	@Override
	public boolean addConsumo(ConsumoDTO consumo) {
		boolean addOk = false;

		ConsumoEntity consumoEntity = new ConsumoEntity();
		consumoEntity.setIdReserva(consumo.getReserva().getId());
		consumoEntity.setIdProducto(consumo.getProducto().getId());
		consumoEntity.setCantidad(consumo.getCantidad());
		consumoEntity.setPrecio(consumo.getPrecio());

		if (consumoDAO.addConsumo(consumoEntity)) {
			addOk = true;
		}

		return addOk;
	}

	@Override
	public boolean updateConsumo(ConsumoDTO consumo) {
		boolean addOk = false;

		ConsumoEntity consumoEntity = new ConsumoEntity();
		consumoEntity.setId(consumo.getId());
		consumoEntity.setIdReserva(consumo.getReserva().getId());
		consumoEntity.setIdProducto(consumo.getProducto().getId());
		consumoEntity.setCantidad(consumo.getCantidad());
		consumoEntity.setPrecio(consumo.getPrecio());

		if (consumoDAO.updateConsumo(consumoEntity)) {
			addOk = true;
		}

		return addOk;
	}

	@Override
	public boolean deleteConsumo(int id) {

		boolean deleteOk = false;
		
		if (consumoDAO.deleteConsumo(id)) {
			deleteOk = true;
		}
		
		return deleteOk;
	}

}
