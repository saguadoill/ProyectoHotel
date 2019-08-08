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
import com.capgemini.dtos.ClienteDTO;
import com.capgemini.dtos.HabitacionDTO;
import com.capgemini.dtos.HotelDTO;
import com.capgemini.services.impls.IClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProyectoHotelesMvcApplication.class)
@AutoConfigureMockMvc
public class TestClienteController {
		
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	IClienteService iClienteService;
	

	// ------ Test GET listar ------ //
	
	@Test
	public void getAllClientesAPI() throws Exception
	{
	  mvc.perform( MockMvcRequestBuilders
	      .get("/cliente/lista")
	      .accept(MediaType.APPLICATION_JSON))
	      .andDo(print())
	      .andExpect(status().isOk())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.[*]").exists())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.[*]").isNotEmpty());
	}
	 
	
	// ------ Test GET buscarCliente ------ //
	
	@Test
	public void getClienteByIdAPI() throws Exception
	{
	  mvc.perform( MockMvcRequestBuilders
	      .get("/cliente/{id}", 1)
	      .accept(MediaType.APPLICATION_JSON))
	      .andDo(print())
	      .andExpect(status().isOk())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
	}
	
	// ----- Test POST addCliente ----- //
	
	@Test
	public void addClienteAPI() throws Exception
	{	
		
		ClienteDTO cliente_prueba = iClienteService.findById(1);
		cliente_prueba.setDni("10890540V");
		
		mvc.perform(MockMvcRequestBuilders
	      .post("/cliente")
	      .content(asJsonString(cliente_prueba))
	      .contentType(MediaType.APPLICATION_JSON)
	      .accept(MediaType.APPLICATION_JSON))
		  .andExpect(status().isCreated());
	     
	}
	 
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
	// ------ Test PUT updateCliente ------ // 
	
	@Test
	public void updateClienteAPI() throws Exception
	{
		
		ClienteDTO cliente_prueba = iClienteService.findById(1);
		cliente_prueba.setDni("77889900P");
		
		mvc.perform( MockMvcRequestBuilders
	      .put("/cliente")
	      .content(asJsonString(cliente_prueba))
	      .contentType(MediaType.APPLICATION_JSON)
	      .accept(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk());
	      
	}
	
	
	// ----- Test DELETE deleteCliente ------ //
	
	/*
	@Test
	public void deleteClienteAPI() throws Exception
	{
	  mvc.perform( MockMvcRequestBuilders.delete("/cliente/{id}", 11) )
	        .andExpect(status().isOk());
	}
	*/
	
}
