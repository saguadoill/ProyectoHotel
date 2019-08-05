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

import com.capgemini.dtos.PagoDTO;
import com.capgemini.services.impls.IPagoService;


@RestController
@RequestMapping("/pago")
public class PagoController {
	
	@Autowired
	IPagoService iPagoService;

	@RequestMapping(value  = "/lista", method = RequestMethod.GET)
	public ResponseEntity<List<PagoDTO>> listaPagos(){
		return new ResponseEntity<>(iPagoService.findAll(),HttpStatus.OK);
	}
	
	
	@RequestMapping(value  = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<PagoDTO> buscarPago(@PathVariable(name = "id") int id){
		return new ResponseEntity<>(iPagoService.findById(id),HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<PagoDTO> updatePago(@RequestBody PagoDTO pagoDTO){
		HttpStatus respuesta = null;
		
		if (iPagoService.updatePago(pagoDTO)) {
			respuesta = HttpStatus.CREATED;
		} else {
			respuesta = HttpStatus.BAD_REQUEST;
		}
		
		return new ResponseEntity<>(respuesta);
	
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<HttpStatus> addPago(@RequestBody PagoDTO pagoDTO){
		HttpStatus respuesta = null;
		
		if (iPagoService.addPago(pagoDTO)) {
			respuesta = HttpStatus.CREATED;
		}else {
			respuesta = HttpStatus.BAD_REQUEST;
		}
		
		return new ResponseEntity<HttpStatus>(respuesta);
	}
	
	
	@RequestMapping(value  = "/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<HttpStatus> deletePago(@PathVariable(name = "id") int id){
	HttpStatus respuesta = null;
		
		if (iPagoService.deletePago(id)) {
			respuesta = HttpStatus.OK;
		} else {
			respuesta = HttpStatus.BAD_REQUEST;
		}
		
		return new ResponseEntity<HttpStatus>(respuesta);
	}
}
