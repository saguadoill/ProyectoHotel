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

import com.capgemini.dtos.ProductoDTO;
import com.capgemini.services.impls.IProductoService;


@RestController
@RequestMapping("/producto")
public class ProductoController {
	
	@Autowired
	IProductoService iProductoService;

	@RequestMapping(value  = "/lista", method = RequestMethod.GET)
	public ResponseEntity<List<ProductoDTO>> listaProductos(){
		return new ResponseEntity<>(iProductoService.findAll(),HttpStatus.OK);
	}
	
	
	@RequestMapping(value  = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ProductoDTO> buscarProducto(@PathVariable(name = "id") int id){
		return new ResponseEntity<>(iProductoService.findProductoById(id),HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<HttpStatus> addProducto(@RequestBody ProductoDTO productoDTO){
		HttpStatus respuesta = null;
		
		if (iProductoService.addProducto(productoDTO)) {
			respuesta = HttpStatus.CREATED;
		}else {
			respuesta = HttpStatus.BAD_REQUEST;
		}
		
		return new ResponseEntity<HttpStatus>(respuesta);
	}
	
	//TODO: decidir si es mejor enviar el id por formulario o url
	@RequestMapping(value  = "/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<HttpStatus> deleteProducto(@PathVariable(name = "id") int id){
	HttpStatus respuesta = null;
		
		if (iProductoService.deleteProducto(id)) {
			respuesta = HttpStatus.OK;
		}else {
			respuesta = HttpStatus.BAD_REQUEST;
		}
		
		return new ResponseEntity<HttpStatus>(respuesta);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<HttpStatus> updateProducto(@RequestBody ProductoDTO productoDTO){
		HttpStatus respuesta = HttpStatus.BAD_REQUEST;
		if (iProductoService.updateProducto(productoDTO)) {
			respuesta = HttpStatus.OK;
		}
		return new ResponseEntity<HttpStatus>(respuesta);
	}

}
