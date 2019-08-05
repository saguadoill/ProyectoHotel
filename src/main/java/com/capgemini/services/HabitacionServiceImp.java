package com.capgemini.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.daos.HabitacionDAO;
import com.capgemini.dtos.HabitacionDTO;
import com.capgemini.entities.HabitacionEntity;
import com.capgemini.services.impls.IHabitacionService;
import com.capgemini.services.impls.IHotelService;

@Service
public class HabitacionServiceImp implements IHabitacionService {

	@Autowired
	HabitacionDAO habitacionDao;

	@Autowired
	IHotelService hotelService;

	@Override
	public List<HabitacionDTO> findAll() {
		List<HabitacionEntity> listaHabitacionesEntity = new ArrayList<>();
		List<HabitacionDTO> listaHabitacionesDto = new ArrayList<>();

		listaHabitacionesEntity = habitacionDao.findAll();

		for (HabitacionEntity habitacion : listaHabitacionesEntity) {
			HabitacionDTO habitacionDto = new HabitacionDTO();

			habitacionDto.setId(habitacion.getId());
			habitacionDto.setPiso(habitacion.getPiso());
			habitacionDto.setNumero(habitacion.getNumero());
			habitacionDto.setVista(habitacion.getVista());
			habitacionDto.setClase(habitacion.getClase());
			habitacionDto.setCamas(habitacion.getCamas());
			habitacionDto.setPrecio(habitacion.getPrecio());
			habitacionDto.setPersonas(habitacion.getPersonas());
			habitacionDto.setEstado(habitacion.getEstado());
			habitacionDto.setHotel(hotelService.findById(habitacion.getIdHotel()));

			listaHabitacionesDto.add(habitacionDto);
		}

		return listaHabitacionesDto;
	}

	@Override
	public HabitacionDTO findHabitacionById(int id) {
		HabitacionEntity habitacion = habitacionDao.findHabitacionById(id);
		HabitacionDTO habitacionDto = new HabitacionDTO();

		habitacionDto.setId(habitacion.getId());
		habitacionDto.setPiso(habitacion.getPiso());
		habitacionDto.setNumero(habitacion.getNumero());
		habitacionDto.setVista(habitacion.getVista());
		habitacionDto.setClase(habitacion.getClase());
		habitacionDto.setCamas(habitacion.getCamas());
		habitacionDto.setPrecio(habitacion.getPrecio());
		habitacionDto.setPersonas(habitacion.getPersonas());
		habitacionDto.setEstado(habitacion.getEstado());
		habitacionDto.setHotel(hotelService.findById(habitacion.getIdHotel()));

		return habitacionDto;
	}

	@Override
	public boolean addHabitacion(HabitacionDTO habitacion) {
		boolean addOk = false;
		HabitacionEntity habitacionEntity = new HabitacionEntity();

		habitacionEntity.setId(habitacion.getId());
		habitacionEntity.setPiso(habitacion.getPiso());
		habitacionEntity.setNumero(habitacion.getNumero());
		habitacionEntity.setVista(habitacion.getVista());
		habitacionEntity.setClase(habitacion.getClase());
		habitacionEntity.setCamas(habitacion.getCamas());
		habitacionEntity.setPrecio(habitacion.getPrecio());
		habitacionEntity.setPersonas(habitacion.getPersonas());
		habitacionEntity.setEstado(habitacion.getEstado());
		habitacionEntity.setIdHotel(habitacion.getHotel().getId());

		if (habitacionDao.addHabitacion(habitacionEntity)) {
			addOk = true;
		}

		return addOk;
	}

	@Override
	public boolean updateHabitacion(HabitacionDTO habitacion) {
		boolean updateOk = false;
		
		HabitacionEntity  habitacionEntity = habitacionDao.findHabitacionById(habitacion.getId());
		habitacionEntity.setPiso(habitacion.getPiso());
		habitacionEntity.setNumero(habitacion.getNumero());
		habitacionEntity.setVista(habitacion.getVista());
		habitacionEntity.setClase(habitacion.getClase());
		habitacionEntity.setCamas(habitacion.getCamas());
		habitacionEntity.setPrecio(habitacion.getPrecio());
		habitacionEntity.setPersonas(habitacion.getPersonas());
		habitacionEntity.setEstado(habitacion.getEstado());
		habitacionEntity.setIdHotel(habitacion.getHotel().getId());
		
		if (habitacionDao.updateHabitacion(habitacionEntity)) {
			updateOk = true;
		}
		
		return updateOk;
	}

	@Override
	public boolean deleteHabitacion(int id) {
		boolean deleteOk = false;

		if (habitacionDao.deleteHabitacion(id)) {
			deleteOk = true;
		}

		return deleteOk;
	}

}
