package com.capgemini.services.impls;

import java.util.List;

import com.capgemini.dtos.ClienteDTO;


public interface IClienteService {
	
	List<ClienteDTO> findAll();
	
	ClienteDTO findById(int id);
	
	boolean addCliente(ClienteDTO clienteDTO);
	
	boolean updateCliente(ClienteDTO clienteDTO);
	
	boolean deleteCliente(int id);
	
	ClienteDTO findCliente(String email, String password);
}
