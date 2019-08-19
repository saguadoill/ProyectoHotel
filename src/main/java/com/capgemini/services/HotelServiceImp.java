package com.capgemini.services;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.daos.HotelDAO;
import com.capgemini.dtos.HotelDTO;
import com.capgemini.entities.HotelEntity;
import com.capgemini.services.impls.IHotelService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HotelServiceImp implements IHotelService {

	@Autowired
	HotelDAO hotelDao;
	
	@Autowired
	ImagenServiceImp imagenServiceImp;

	public List<HotelDTO> findAll() {
		List<HotelEntity> listaHotelesEntity = new ArrayList<>();
		List<HotelDTO> listaHotelesDto = new ArrayList<>();

		listaHotelesEntity = hotelDao.findAll();

		for (HotelEntity hotel : listaHotelesEntity) {
			HotelDTO hotelDTO = new HotelDTO();
			hotelDTO.setId(hotel.getId());
			hotelDTO.setNombre(hotel.getNombre());
			hotelDTO.setCategoria(hotel.getCategoria());
			hotelDTO.setDireccion(hotel.getDireccion());
			hotelDTO.setZona(hotel.getZona());
			hotelDTO.setImagen(imagenServiceImp.loadImagen(hotel.getImagenUrl()));
			listaHotelesDto.add(hotelDTO);
		}

		return listaHotelesDto;
	}

	@Override
	public HotelDTO findById(int id) {
		HotelDTO hotelDTO = new HotelDTO();
		HotelEntity hotelEntity = hotelDao.findById(id);

		hotelDTO.setId(hotelEntity.getId());
		hotelDTO.setNombre(hotelEntity.getNombre());
		hotelDTO.setCategoria(hotelEntity.getCategoria());
		hotelDTO.setDireccion(hotelEntity.getDireccion());
		hotelDTO.setZona(hotelEntity.getZona());
		hotelDTO.setImagen(imagenServiceImp.loadImagen(hotelEntity.getImagenUrl()));

		return hotelDTO;
	}

	@Override
	public boolean addHotel(HotelDTO hotelDTO) {
		boolean addOk = false;

		HotelEntity hotelEntity = new HotelEntity();
		hotelEntity.setNombre(hotelDTO.getNombre());
		hotelEntity.setCategoria(hotelDTO.getCategoria());
		hotelEntity.setDireccion(hotelDTO.getDireccion());
		hotelEntity.setZona(hotelDTO.getZona());

		if (hotelDao.addHotel(hotelEntity)) {
			addOk = true;
		}

		return addOk;
	}

	@Override
	public boolean updateHotel(HotelDTO hotelDTO) {
		boolean updateOk = false;

		HotelEntity hotelEntity = hotelDao.findById(hotelDTO.getId());
		hotelEntity.setNombre(hotelDTO.getNombre());
		hotelEntity.setCategoria(hotelDTO.getCategoria());
		hotelEntity.setDireccion(hotelDTO.getDireccion());
		hotelEntity.setZona(hotelDTO.getZona());

		if (hotelDao.updateHotel(hotelEntity)) {
			updateOk = true;
		}

		return updateOk;
	}

	@Override
	public boolean deleteHotel(int id) {
		boolean deleteOk = false;

		if (hotelDao.deleteHotel(id)) {
			deleteOk = true;
		}

		return deleteOk;
	}
	
}
