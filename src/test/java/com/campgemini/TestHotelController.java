package com.campgemini;

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

import com.capgemini.ProyectoHotelesMvcApplication;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProyectoHotelesMvcApplication.class)
@AutoConfigureMockMvc
public class TestHotelController {

	@Autowired
	private MockMvc mvc;
	
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

}
