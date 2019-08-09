package com.capgemini.services.impls;

import java.util.List;

import com.capgemini.dtos.ClienteDTO;
import com.fasterxml.jackson.core.JsonProcessingException;


public interface IClienteService {
	
	List<ClienteDTO> findAll();
	
	ClienteDTO findById(int id);
	
	ClienteDTO findByLogin(String email) throws JsonProcessingException;
	
	boolean addCliente(ClienteDTO clienteDTO);
	
	boolean updateCliente(ClienteDTO clienteDTO);
	
	boolean deleteCliente(int id);
}
