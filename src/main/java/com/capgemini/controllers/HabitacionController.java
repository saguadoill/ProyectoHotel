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

import com.capgemini.dtos.HabitacionDTO;
import com.capgemini.services.impls.IHabitacionService;




@RestController
@RequestMapping("/habitacion")
public class HabitacionController {
	
	
	@Autowired
	IHabitacionService iHabitacionService;

	@RequestMapping(value  = "/lista", method = RequestMethod.GET)
	public ResponseEntity<List<HabitacionDTO>> listaHabitaciones(){
		return new ResponseEntity<>(iHabitacionService.findAll(),HttpStatus.OK);
	}
	
	
	@RequestMapping(value  = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<HabitacionDTO> buscarHabitacion(@PathVariable(name = "id") int id){
		return new ResponseEntity<>(iHabitacionService.findHabitacionById(id),HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<HttpStatus> addHabitacion(@RequestBody HabitacionDTO habitacionDTO){
		HttpStatus respuesta = null;
		
		if (iHabitacionService.addHabitacion(habitacionDTO)) {
			respuesta = HttpStatus.CREATED;
		}else {
			respuesta = HttpStatus.BAD_REQUEST;
		}
		
		return new ResponseEntity<HttpStatus>(respuesta);
	}
	
	//TODO: decidir si es mejor enviar el id por formulario o url
	@RequestMapping(value  = "/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<HttpStatus> deleteHabitacion(@PathVariable(name = "id") int id){
	HttpStatus respuesta = null;
		
		if (iHabitacionService.deleteHabitacion(id)) {
			respuesta = HttpStatus.OK;
		}else {
			respuesta = HttpStatus.BAD_REQUEST;
		}
		
		return new ResponseEntity<HttpStatus>(respuesta);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<HttpStatus> updateHabitacion(@RequestBody HabitacionDTO habitacionDTO){
		HttpStatus respuesta = HttpStatus.BAD_REQUEST;
		if (iHabitacionService.updateHabitacion(habitacionDTO)) {
			respuesta = HttpStatus.OK;
		}
		return new ResponseEntity<HttpStatus>(respuesta);
	}
	

}
