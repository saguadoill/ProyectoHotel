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

import com.capgemini.dtos.ConsumoDTO;
import com.capgemini.services.impls.IConsumoService;

@RestController
@RequestMapping("/consumo")
public class ConsumoController {
	
	@Autowired
	IConsumoService consumoService;
	
	@RequestMapping(value  = "/lista", method = RequestMethod.GET)
	public ResponseEntity<List<ConsumoDTO>> listaCconsumos(){
		return new ResponseEntity<>(consumoService.findAll(),HttpStatus.OK);
	}
	
	
	@RequestMapping(value  = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ConsumoDTO> buscarConsumo(@PathVariable(name = "id") int id){
		return new ResponseEntity<>(consumoService.findById(id),HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<ConsumoDTO> updateConsumo(@RequestBody ConsumoDTO consumoDTO){
		HttpStatus respuesta = null;
		
		if (consumoService.updateConsumo(consumoDTO)) {
			respuesta = HttpStatus.OK;
		} else {
			respuesta = HttpStatus.BAD_REQUEST;
		}
		
		return new ResponseEntity<>(respuesta);
	
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<HttpStatus> addConsumo(@RequestBody ConsumoDTO consumoDTO){
		HttpStatus respuesta = null;
		
		if (consumoService.addConsumo(consumoDTO)) {
			respuesta = HttpStatus.CREATED;
		}else {
			respuesta = HttpStatus.BAD_REQUEST;
		}
		
		return new ResponseEntity<HttpStatus>(respuesta);
	}
	

	@RequestMapping(value  = "/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<HttpStatus> deleteConsumo(@PathVariable(name = "id") int id){
	HttpStatus respuesta = null;
		
		if (consumoService.deleteConsumo(id)) {
			respuesta = HttpStatus.ACCEPTED;
		} else {
			respuesta = HttpStatus.BAD_REQUEST;
		}
		
		return new ResponseEntity<HttpStatus>(respuesta);
	}

}
