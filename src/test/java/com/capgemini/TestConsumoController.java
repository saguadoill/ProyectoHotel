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

import com.capgemini.dtos.ConsumoDTO;
import com.capgemini.dtos.ProductoDTO;
import com.capgemini.dtos.ReservaDTO;
import com.capgemini.services.impls.IConsumoService;
import com.capgemini.services.impls.IProductoService;
import com.capgemini.services.impls.IReservaService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProyectoHotelesMvcApplication.class)
@AutoConfigureMockMvc
@Slf4j
public class TestConsumoController {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private IReservaService reservaService;
	
	@Autowired
	private IProductoService productoService;
	
	@Autowired
	private IConsumoService consumoService;
	
	@Test
	public void getAllConsumosAPI() throws Exception{
		
	  mvc.perform( MockMvcRequestBuilders
	      .get("/consumo/lista")
	      .accept(MediaType.APPLICATION_JSON))
	      .andDo(print())
	      .andExpect(status().isOk())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.[*]").exists())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.[*]").isNotEmpty());
	}
	 
	@Test
	public void getConsumoByIdAPI() throws Exception{
	  mvc.perform( MockMvcRequestBuilders
	      .get("/consumo/{id}", 1)
	      .accept(MediaType.APPLICATION_JSON))
	      .andDo(print())
	      .andExpect(status().isOk())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
	}
	
	@Test
	public void cearConsumo() throws Exception{
		
		ConsumoDTO consumo = new ConsumoDTO();
		
		ReservaDTO reserva = reservaService.findById(37);
		
		
		ProductoDTO producto = productoService.findProductoById(2);
		
		consumo.setReserva(reserva);
		consumo.setProducto(producto);
		consumo.setCantidad(2);
		consumo.setPrecio(71);
		
		log.info(consumo.toString());
		
		
	  mvc.perform( MockMvcRequestBuilders
	      .post("/consumo")
	      .content(asJsonString(consumo))
	      .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
	      .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
	      .andExpect(status().isCreated());
	}
	
	@Test
	public void updateConsumoPI() throws Exception{
		
		ConsumoDTO consumo = consumoService.findById(5);
		consumo.setCantidad(3);
		consumo.setPrecio(consumo.getProducto().getPrecio()*3);
		
	  mvc.perform( MockMvcRequestBuilders
	      .put("/consumo")
	      .content(asJsonString(consumo))
	      .contentType(MediaType.APPLICATION_JSON)
	      .accept(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk());
	}
	 
	@Test
	public void deleteConsumoAPI() throws Exception
	{
	  mvc.perform( MockMvcRequestBuilders.delete("/consumo/{id}", 6) )
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
