package com.capgemini.daos;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.capgemini.entities.HotelEntity;

@Mapper
public interface HotelDAO {
	
	List<HotelEntity> findAll();
	
	HotelEntity findById(int id);
	
	boolean addHotel(HotelEntity hotel);
	
	boolean updateHotel(HotelEntity hotel);
	
	boolean deleteHotel(int id);
	
	
}
