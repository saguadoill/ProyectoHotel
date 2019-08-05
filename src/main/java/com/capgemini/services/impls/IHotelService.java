package com.capgemini.services.impls;

import java.util.List;

import com.capgemini.dtos.HotelDTO;


public interface IHotelService {
	
	List<HotelDTO> findAll();
	
	HotelDTO findById(int id);
	
	boolean addHotel(HotelDTO hotelDTO);
	
	boolean updateHotel(HotelDTO hotelDTO);
	
	boolean deleteHotel(int id);

}
