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

import com.capgemini.dtos.ReservaDTO;
import com.capgemini.services.impls.IReservaService;

@RestController
@RequestMapping("/reserva")
public class ReservaController {
	
	@Autowired
	IReservaService iReservaService;
	
	@RequestMapping(value  = "/lista", method = RequestMethod.GET)
	public ResponseEntity<List<ReservaDTO>> listaReservas(){
		return new ResponseEntity<>(iReservaService.findAll(),HttpStatus.OK);
	}
	
	
	@RequestMapping(value  = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ReservaDTO> buscarReserva(@PathVariable(name = "id") int id){
		return new ResponseEntity<>(iReservaService.findById(id),HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<ReservaDTO> updateReserva(@RequestBody ReservaDTO reservaDTO){
		HttpStatus respuesta = null;
		
		if (iReservaService.updateReserva(reservaDTO)) {
			respuesta = HttpStatus.CREATED;
		} else {
			respuesta = HttpStatus.BAD_REQUEST;
		}
		
		return new ResponseEntity<>(respuesta);
	
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<HttpStatus> addReserva(@RequestBody ReservaDTO reservaDTO){
		HttpStatus respuesta = null;
		
		if (iReservaService.addReserva(reservaDTO)) {
			respuesta = HttpStatus.CREATED;
		}else {
			respuesta = HttpStatus.BAD_REQUEST;
		}
		
		return new ResponseEntity<HttpStatus>(respuesta);
	}
	

	@RequestMapping(value  = "/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<HttpStatus> deleteReserva(@PathVariable(name = "id") int id){
	HttpStatus respuesta = null;
		
		if (iReservaService.deleteReserva(id)) {
			respuesta = HttpStatus.OK;
		} else {
			respuesta = HttpStatus.BAD_REQUEST;
		}
		
		return new ResponseEntity<HttpStatus>(respuesta);
	}
	
	
}
