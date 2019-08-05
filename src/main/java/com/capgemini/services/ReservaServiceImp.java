package com.capgemini.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.daos.ReservaDAO;
import com.capgemini.dtos.ReservaDTO;
import com.capgemini.entities.ReservaEntity;
import com.capgemini.services.impls.IClienteService;
import com.capgemini.services.impls.IHabitacionService;
import com.capgemini.services.impls.IReservaService;

@Service
public class ReservaServiceImp implements IReservaService {
	
	@Autowired
	ReservaDAO reservaDao;
	
	@Autowired
	IClienteService clienteService;
	
	@Autowired
	IHabitacionService habitacionService;
		
		@Override
		public List<ReservaDTO> findAll() {
			
			List<ReservaEntity> listaReservasEntity = new ArrayList<>();
			List<ReservaDTO> listaReservasDTO = new ArrayList<>();
			
			listaReservasEntity = reservaDao.findAll();
			
			for (ReservaEntity reservaEntity : listaReservasEntity) {
				
				ReservaDTO reservaDTO = new ReservaDTO();
				
				reservaDTO.setId(reservaEntity.getId());
				reservaDTO.setClienteDTO(clienteService.findById(reservaEntity.getIdCliente()));
				reservaDTO.setHabitacionDTO(habitacionService.findHabitacionById(reservaEntity.getIdHabitacion()));
				reservaDTO.setFechaReserva(reservaEntity.getFechaReserva());
				reservaDTO.setFechaInicio(reservaEntity.getFechaInicio());
				reservaDTO.setFechaFin(reservaEntity.getFechaFin());
				reservaDTO.setCosteAlojamiento(reservaEntity.getCosteAlojamiento());
				reservaDTO.setEstado(reservaEntity.getEstado());
					
				listaReservasDTO.add(reservaDTO);
			}
			
			return listaReservasDTO;
		}
	
		@Override
		public ReservaDTO findById(int id) {
			
			ReservaDTO reservaDTO = new ReservaDTO();
			ReservaEntity reservaEntity = reservaDao.findById(id);
			
			reservaDTO.setId(reservaEntity.getId());
			reservaDTO.setClienteDTO(clienteService.findById(reservaEntity.getIdCliente()));
			reservaDTO.setHabitacionDTO(habitacionService.findHabitacionById(reservaEntity.getIdHabitacion()));
			reservaDTO.setFechaReserva(reservaEntity.getFechaReserva());
			reservaDTO.setFechaInicio(reservaEntity.getFechaInicio());
			reservaDTO.setFechaFin(reservaEntity.getFechaFin());
			reservaDTO.setCosteAlojamiento(reservaEntity.getCosteAlojamiento());
			reservaDTO.setEstado(reservaEntity.getEstado());
			
			return reservaDTO;
			
		}
	
		@Override
		public boolean addReserva(ReservaDTO reservaDTO) {
			
			boolean addOk = false;
			
			ReservaEntity reservaEntity = new ReservaEntity();
			reservaEntity.setId(reservaDTO.getClienteDTO().getCli_id());
			reservaEntity.setIdHabitacion(reservaDTO.getHabitacionDTO().getId());
			reservaEntity.setFechaReserva(reservaDTO.getFechaReserva());
			reservaEntity.setFechaInicio(reservaDTO.getFechaInicio());
			reservaEntity.setFechaFin(reservaDTO.getFechaFin());
			reservaEntity.setCosteAlojamiento(costeAlojamiento(reservaDTO));
			reservaEntity.setEstado(reservaDTO.getEstado());
			
			if (reservaDao.addReserva(reservaEntity)) {
				addOk = true;
			}
			return addOk;
		}
	
		@Override
		public boolean updateReserva(ReservaDTO reservaDTO) {
			
			boolean updateOk = false;
			
			ReservaEntity reservaEntity = new ReservaEntity();
			reservaEntity.setId(reservaDTO.getClienteDTO().getCli_id());
			reservaEntity.setIdHabitacion(reservaDTO.getHabitacionDTO().getId());
			reservaEntity.setFechaReserva(reservaDTO.getFechaReserva());
			reservaEntity.setFechaInicio(reservaDTO.getFechaInicio());
			reservaEntity.setFechaFin(reservaDTO.getFechaFin());
			reservaEntity.setCosteAlojamiento(costeAlojamiento(reservaDTO));
			reservaEntity.setEstado(reservaDTO.getEstado());
			
			if (reservaDao.updateReserva(reservaEntity)) {
				updateOk = true;
			}
			
			return updateOk;
		}
	
		@Override
		public boolean deleteReserva(int id) {
			
			boolean deleteOk = false;
			
			if (reservaDao.deleteReserva(id)) {
				deleteOk = true;
			}
			
			return deleteOk;
		}
		
		private float costeAlojamiento(ReservaDTO reservaDTO) {
			float total = 0;
			
			float precioHabitacion = reservaDTO.getHabitacionDTO().getPrecio();
			float precioCounsumos = 0;// buscar consupo po el id de la reserva.
			
			total = precioHabitacion + precioCounsumos;		
			
			return total;
		}
	
}
