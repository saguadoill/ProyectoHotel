package com.capgemini;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.capgemini.ProyectoHotelesMvcApplication;
import com.capgemini.dtos.HabitacionDTO;
import com.capgemini.dtos.HotelDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProyectoHotelesMvcApplication.class)
@AutoConfigureMockMvc
public class TestHotelController {
	
	@Autowired
	private MockMvc mvc;
	

	// ------ Test GET listarHoteles ------ //
	
	@Test
	public void getAllHotelesAPI() throws Exception
	{
	  mvc.perform( MockMvcRequestBuilders
	      .get("/hotel/lista")
	      .accept(MediaType.APPLICATION_JSON))
	      .andDo(print())
	      .andExpect(status().isOk())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.[*]").exists())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.[*]").isNotEmpty());
	}
	 
	
	// ------ Test GET buscarHotel ------ //
	
	@Test
	public void getHotelByIdAPI() throws Exception
	{
	  mvc.perform( MockMvcRequestBuilders
	      .get("/hotel/{id}", 1)
	      .accept(MediaType.APPLICATION_JSON))
	      .andDo(print())
	      .andExpect(status().isOk())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
	}
	
	// ----- Test POST addHotel ----- //
	
	@Test
	public void addHotelAPI() throws Exception
	{	
		List<HabitacionDTO> habitaciones = new ArrayList<HabitacionDTO>();
		HotelDTO hotelPrueba = new HotelDTO();
		hotelPrueba.setNombre("El dorado");
		hotelPrueba.setCategoria("4 estrellas");
		hotelPrueba.setDireccion("Desierto de dios");
		hotelPrueba.setZona("Ni se sabe la zona");
		hotelPrueba.setListaHabitaciones(habitaciones);
		
		mvc.perform(MockMvcRequestBuilders
	      .post("/hotel")
	      .content(asJsonString(hotelPrueba))
	      .contentType(MediaType.APPLICATION_JSON)
	      .accept(MediaType.APPLICATION_JSON))
		  .andExpect(status().isCreated());
	      //.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
	     
	}
	 
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
	// ------ Test PUT updateHotel ------ // 
	
	@Test
	public void updateHotelAPI() throws Exception
	{
		
		List<HabitacionDTO> habitaciones = new ArrayList<HabitacionDTO>();
		HotelDTO hotelPrueba = new HotelDTO();
		hotelPrueba.setId(15);
		hotelPrueba.setNombre("El plateadoUpdate");
		hotelPrueba.setCategoria("3 estrellas");
		hotelPrueba.setDireccion("Desierto de dios");
		hotelPrueba.setZona("Ni se sabe la zona");
		hotelPrueba.setListaHabitaciones(habitaciones);
		
	  mvc.perform( MockMvcRequestBuilders
	      .put("/hotel")
	      .content(asJsonString(hotelPrueba))
	      .contentType(MediaType.APPLICATION_JSON)
	      .accept(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk());
	      
	}
	
	
	// ----- Test DELETE deleteHotel ------ //
	
	@Test
	public void deleteHotelAPI() throws Exception
	{
	  mvc.perform( MockMvcRequestBuilders.delete("/hotel/{id}", 15) )
	        .andExpect(status().isOk());
	}
	
	
}
