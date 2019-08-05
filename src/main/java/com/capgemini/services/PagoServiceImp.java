package com.capgemini.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.daos.PagoDAO;
import com.capgemini.dtos.PagoDTO;
import com.capgemini.entities.PagoEntity;
import com.capgemini.services.impls.IPagoService;

@Service
public class PagoServiceImp implements IPagoService{
	
	@Autowired
	PagoDAO pagoDao;

		@Override
		public List<PagoDTO> findAll() {
			
			List<PagoEntity> listaPagosEntity = new ArrayList<>();
			List<PagoDTO> listaPagoDTO = new ArrayList<>();
			
			listaPagosEntity = pagoDao.findAll();
			
			for (PagoEntity pagoEntity : listaPagosEntity) {
				
				PagoDTO pagoDTO = new PagoDTO();
				
				pagoDTO.setId(pagoEntity.getId());
				pagoDTO.setRe_id(pagoEntity.getRe_id());
				pagoDTO.setTotal(pagoEntity.getTotal());
				pagoDTO.setFecha_pago(pagoEntity.getFecha_pago());
				
				listaPagoDTO.add(pagoDTO);
			}
			
			return listaPagoDTO;
		}
	
		@Override
		public PagoDTO findById(int id) {
			
			PagoDTO pagoDTO = new PagoDTO();
			PagoEntity pagoEntity = pagoDao.findById(id);
			
			pagoDTO.setId(pagoEntity.getId());
			pagoDTO.setRe_id(pagoEntity.getRe_id());
			pagoDTO.setTotal(pagoEntity.getTotal());
			pagoDTO.setFecha_pago(pagoEntity.getFecha_pago());
			
			return pagoDTO;
			
		}
	
		@Override
		public boolean addPago(PagoDTO pagoDTO) {
			
			boolean addOk = false;
			
			PagoEntity pagoEntity = new PagoEntity();
			
			pagoEntity.setId(pagoDTO.getId());
			pagoEntity.setRe_id(pagoDTO.getRe_id());
			pagoEntity.setTotal(pagoDTO.getTotal());
			pagoEntity.setFecha_pago(pagoDTO.getFecha_pago());
			
			
			if (pagoDao.addPago(pagoEntity)) {
				addOk = true;
			}
			return addOk;
		}
	
		@Override
		public boolean updatePago(PagoDTO pagoDTO) {
			
			boolean updateOk = false;
			
			PagoEntity pagoEntity = new PagoEntity();
			
			pagoEntity.setRe_id(pagoDTO.getRe_id());
			pagoEntity.setTotal(pagoDTO.getTotal());
			pagoEntity.setFecha_pago(pagoDTO.getFecha_pago());
			
			if (pagoDao.updatePago(pagoEntity)) {
				updateOk = true;
			}
			
			return updateOk;
		}
	
		@Override
		public boolean deletePago(int id) {
			
			boolean deleteOk = false;
			
			if (pagoDao.deletePago(id)) {
				deleteOk = true;
			}
			
			return deleteOk;
		}
	
}
