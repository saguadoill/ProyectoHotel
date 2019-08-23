package com.capgemini.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.dtos.HabitacionDTO;
import com.capgemini.services.impls.IHabitacionService;

import lombok.extern.slf4j.Slf4j;




@RestController
@RequestMapping("/habitacion")
@Slf4j
public class HabitacionController {
	
	
	@Autowired
	IHabitacionService iHabitacionService;

	@RequestMapping(value  = "/lista", method = RequestMethod.GET)
	public ResponseEntity<List<HabitacionDTO>> listaHabitaciones(){
		return new ResponseEntity<>(iHabitacionService.findAll(),HttpStatus.OK);
	}
	
	@RequestMapping(value  = "/disponibles", method = RequestMethod.POST)
	public ResponseEntity<List<HabitacionDTO>> listaHabitaciones(@RequestBody String json) {
		
		log.info("ha controller: "+json);
		
		JSONObject objeto = new JSONObject(json);
		
		log.info(objeto.getString("fechaInicio"));
		
		String fechaIni = objeto.getString("fechaInicio");
		String fechaFin = objeto.getString("fechaFin");

	
		LocalDate fechaInicioLocalDate = LocalDate.parse(fechaIni);
		LocalDate fechaFinLocalDate = LocalDate.parse(fechaFin);
		
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//		
//		//convert String to LocalDate
//		LocalDate fechaInicioLocalDate = LocalDate.parse(fechaIni, formatter);
//		LocalDate fechaInicioLocalDate = LocalDate.parse(fechaIni, formatter);
		
		int idHotel = objeto.getInt("idHotel");
		List<HabitacionDTO> listaHabitaciones = iHabitacionService.findHabitacionesDisponiblesHotel(idHotel, fechaInicioLocalDate, fechaFinLocalDate);
		
		return new ResponseEntity<>(listaHabitaciones,HttpStatus.OK);
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
