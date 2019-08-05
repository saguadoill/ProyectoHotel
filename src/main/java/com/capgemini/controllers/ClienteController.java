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

import com.capgemini.dtos.ClienteDTO;
import com.capgemini.services.impls.IClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	IClienteService clienteService;
	
	@RequestMapping(value  = "/lista", method = RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> listaClientes(){
		return new ResponseEntity<>(clienteService.findAll(),HttpStatus.OK);
	}
	
	
	@RequestMapping(value  = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ClienteDTO> buscarCliente(@PathVariable(name = "id") int id){
		return new ResponseEntity<>(clienteService.findById(id),HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<ClienteDTO> updateCliente(@RequestBody ClienteDTO clienteDTO){
		HttpStatus respuesta = null;
		
		if (clienteService.updateCliente(clienteDTO)) {
			respuesta = HttpStatus.CREATED;
		} else {
			respuesta = HttpStatus.BAD_REQUEST;
		}
		
		
		return new ResponseEntity<>(respuesta);
	
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<HttpStatus> addCliente(@RequestBody ClienteDTO clienteDTO){
		HttpStatus respuesta = null;
		
		if (clienteService.addCliente(clienteDTO)) {
			respuesta = HttpStatus.CREATED;
		}else {
			respuesta = HttpStatus.BAD_REQUEST;
		}
		
		return new ResponseEntity<HttpStatus>(respuesta);
	}
	

	@RequestMapping(value  = "/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<HttpStatus> deleteCliente(@PathVariable(name = "id") int id){
	HttpStatus respuesta = null;
		
		if (clienteService.deleteCliente(id)) {
			respuesta = HttpStatus.OK;
		} else {
			respuesta = HttpStatus.BAD_REQUEST;
		}
		
		return new ResponseEntity<HttpStatus>(respuesta);
	}
	
}
