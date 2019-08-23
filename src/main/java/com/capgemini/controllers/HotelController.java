package com.capgemini.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.dtos.HotelDTO;
import com.capgemini.services.impls.IHotelService;

//import lombok.extern.slf4j.Slf4j;

@RestController
//@Slf4j
@RequestMapping("/hotel")
public class HotelController {
	
	@Autowired
	IHotelService iHoteleService;

	@RequestMapping(value  = "/lista", method = RequestMethod.GET)
	public ResponseEntity<List<HotelDTO>> listaHoteles(){
		return new ResponseEntity<>(iHoteleService.findAll(),HttpStatus.OK);
	}
	
	@RequestMapping(value  = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<HotelDTO> buscarHotel(@PathVariable(name = "id") int id){
		return new ResponseEntity<>(iHoteleService.findById(id),HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<HttpStatus> addHotel(@RequestBody HotelDTO hotelDTO){
		HttpStatus respuesta = null;
		
		if (iHoteleService.addHotel(hotelDTO)) {
			respuesta = HttpStatus.CREATED;
		}else {
			respuesta = HttpStatus.BAD_REQUEST;
		}
		
		return new ResponseEntity<HttpStatus>(respuesta);
	}
	
	//TODO: decidir si es mejor enviar el id por formulario o url
	@RequestMapping(value  = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<HttpStatus> deleteHotel(@PathVariable(name = "id") int id){
	HttpStatus respuesta = null;
		
		if (iHoteleService.deleteHotel(id)) {
			respuesta = HttpStatus.OK;
		} else {
			respuesta = HttpStatus.BAD_REQUEST;
		}
		
		return new ResponseEntity<HttpStatus>(respuesta);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<HttpStatus> updateHotel(@RequestBody HotelDTO hotelDTO){
		HttpStatus respuesta = HttpStatus.BAD_REQUEST;
		if (iHoteleService.updateHotel(hotelDTO)) {
			respuesta = HttpStatus.OK;
		}
		return new ResponseEntity<HttpStatus>(respuesta);
	}
}
