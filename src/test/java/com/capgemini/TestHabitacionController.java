package com.capgemini;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dtos.HabitacionDTO;
import com.capgemini.dtos.HotelDTO;
import com.capgemini.services.impls.IHotelService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProyectoHotelesMvcApplication.class)
@AutoConfigureMockMvc
public class TestHabitacionController {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private IHotelService hotelService;
	
	@Test
	public void getAllHabitacionesAPI() throws Exception{
		
	  mvc.perform( MockMvcRequestBuilders
	      .get("/habitacion/lista")
	      .accept(MediaType.APPLICATION_JSON))
	      .andDo(print())
	      .andExpect(status().isOk())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.[*]").exists())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.[*]").isNotEmpty());
	}
	 
	@Test
	public void getHabitacionByIdAPI() throws Exception{
	  mvc.perform( MockMvcRequestBuilders
	      .get("/habitacion/{id}", 2)
	      .accept(MediaType.APPLICATION_JSON))
	      .andDo(print())
	      .andExpect(status().isOk())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2));
	}
	
	@Test
	public void cearHabitacionAPI() throws Exception{
		
		HabitacionDTO habitacion = new HabitacionDTO();
		habitacion.setPiso(2);
		habitacion.setNumero(12);
		habitacion.setVista("al mar");
		habitacion.setClase("primera");
		habitacion.setCamas(2);
		habitacion.setPrecio(130);
		habitacion.setPersonas(2);
		habitacion.setEstado("libre");
		
		HotelDTO hotel = hotelService.findById(1);
		
		habitacion.setHotel(hotel);
		
	  mvc.perform( MockMvcRequestBuilders
	      .post("/habitacion")
	      .content(asJsonString(habitacion))
	      .contentType(MediaType.APPLICATION_JSON)
	      .accept(MediaType.APPLICATION_JSON))
	      .andExpect(status().isCreated());
	}
	
	@Test
	public void updateHabitacionAPI() throws Exception{
		
		HabitacionDTO habitacion = new HabitacionDTO();
		habitacion.setId(2);
		habitacion.setPiso(2);
		habitacion.setNumero(12);
		habitacion.setVista("al mar");
		habitacion.setClase("primera");
		habitacion.setCamas(2);
		habitacion.setPrecio(130);
		habitacion.setPersonas(2);
		habitacion.setEstado("libre");
		
		HotelDTO hotel = new HotelDTO();
		hotel.setId(1);
		hotel.setNombre("Gran Meliá Palacio de los Duques");
		hotel.setCategoria("4 estrellas");
		hotel.setDireccion("Cuesta de Santo Domingo 5 y 7");
		hotel.setZona("Centro");
		
		habitacion.setHotel(hotel);
		
	  mvc.perform( MockMvcRequestBuilders
	      .put("/habitacion")
	      .content(asJsonString(habitacion))
	      .contentType(MediaType.APPLICATION_JSON)
	      .accept(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk());
	}
	 
	@Test
	public void deleteHabitacionAPI() throws Exception
	{
	  mvc.perform( MockMvcRequestBuilders.delete("/habitacion/{id}", 152) )
	        .andExpect(status().isAccepted());
	}
	
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}

}
