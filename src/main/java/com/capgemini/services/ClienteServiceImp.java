package com.capgemini.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.daos.ClienteDAO;
import com.capgemini.dtos.ClienteDTO;
import com.capgemini.entities.ClienteEntity;
import com.capgemini.services.impls.IClienteService;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClienteServiceImp implements IClienteService {
	
	@Autowired
	ClienteDAO clienteDao;
	
	@Override
	public List<ClienteDTO> findAll() {
		List<ClienteEntity> listaClientesEntity = clienteDao.findAll();
		List<ClienteDTO> listClientesDTO = new ArrayList<>();

		
		for (ClienteEntity cliente : listaClientesEntity) {
			ClienteDTO clienteDTO = new ClienteDTO();
			clienteDTO.setId(cliente.getId());
			clienteDTO.setDni(cliente.getDni());
			clienteDTO.setNombre(cliente.getNombre());
			clienteDTO.setApellido(cliente.getApellido());
			clienteDTO.setEmail(cliente.getEmail());
			clienteDTO.setDireccion(cliente.getDireccion());
			clienteDTO.setCodigoPostal(cliente.getCodigoPostal());
			clienteDTO.setCiudad(cliente.getCiudad());
			clienteDTO.setPuntosDescuento(cliente.getPuntosDescuento());
			clienteDTO.setPasswd(cliente.getPassword());
			clienteDTO.setRole(cliente.getRole());
			
			// Añadir consumo
			
			
			listClientesDTO.add(clienteDTO);
		}
		
		return listClientesDTO;
	}

	@Override
	public ClienteDTO findById(int id) {
		
		ClienteDTO clienteDTO = new ClienteDTO();
		ClienteEntity clienteEntity = clienteDao.findById(id);
		
		clienteDTO.setId(clienteEntity.getId());
		clienteDTO.setDni(clienteEntity.getDni());
		clienteDTO.setNombre(clienteEntity.getNombre());
		clienteDTO.setApellido(clienteEntity.getApellido());
		clienteDTO.setEmail(clienteEntity.getEmail());
		clienteDTO.setDireccion(clienteEntity.getDireccion());
		clienteDTO.setCodigoPostal(clienteEntity.getCodigoPostal());
		clienteDTO.setCiudad(clienteEntity.getCiudad());
		clienteDTO.setPuntosDescuento(clienteEntity.getPuntosDescuento());
		clienteDTO.setPasswd(clienteEntity.getPassword());
		clienteDTO.setRole(clienteEntity.getRole());
		
		return clienteDTO;
		
	}
	
	@Override
	public ClienteDTO findByLogin(String email) throws JsonProcessingException {
		ClienteDTO clienteDTO = new ClienteDTO();
		ClienteEntity clienteEntity = clienteDao.findByEmail(email);
		
		clienteDTO.setId(clienteEntity.getId());
		clienteDTO.setDni(clienteEntity.getDni());
		clienteDTO.setNombre(clienteEntity.getNombre());
		clienteDTO.setApellido(clienteEntity.getApellido());
		clienteDTO.setEmail(clienteEntity.getEmail());
		clienteDTO.setDireccion(clienteEntity.getDireccion());
		clienteDTO.setCodigoPostal(clienteEntity.getCodigoPostal());
		clienteDTO.setCiudad(clienteEntity.getCiudad());
		clienteDTO.setPuntosDescuento(clienteEntity.getPuntosDescuento());
		clienteDTO.setPasswd(clienteEntity.getPassword());
		clienteDTO.setRole(clienteEntity.getRole());
	
		return clienteDTO;
	}

	@Override
	public boolean addCliente(ClienteDTO clienteDTO) {
		
		boolean addOk = false;
		ClienteEntity clienteEntity = new ClienteEntity();
		clienteEntity.setId(clienteDTO.getId());
		clienteEntity.setDni(clienteDTO.getDni());
		clienteEntity.setNombre(clienteDTO.getNombre());
		clienteEntity.setApellido(clienteDTO.getApellido());
		clienteEntity.setEmail(clienteDTO.getEmail());
		clienteEntity.setDireccion(clienteDTO.getDireccion());
		clienteEntity.setCodigoPostal(clienteDTO.getCodigoPostal());
		clienteEntity.setCiudad(clienteDTO.getCiudad());
		clienteEntity.setPuntosDescuento(clienteDTO.getPuntosDescuento());
		clienteEntity.setPassword(clienteDTO.getPasswd());
		clienteEntity.setRole("ROLE_USER");
		
		if (clienteDao.addCliente(clienteEntity)) {
			addOk = true;
		}
		
		return addOk;
		
	}

	@Override
	public boolean updateCliente(ClienteDTO clienteDTO) {
		
		boolean updateOk = false;
		
		ClienteEntity clienteEntity = new ClienteEntity();
		clienteEntity.setId(clienteDTO.getId());
		clienteEntity.setDni(clienteDTO.getDni());
		clienteEntity.setNombre(clienteDTO.getNombre());
		clienteEntity.setApellido(clienteDTO.getApellido());
		clienteEntity.setEmail(clienteDTO.getEmail());
		clienteEntity.setDireccion(clienteDTO.getDireccion());
		clienteEntity.setCodigoPostal(clienteDTO.getCodigoPostal());
		clienteEntity.setCiudad(clienteDTO.getCiudad());
		clienteEntity.setPuntosDescuento(clienteDTO.getPuntosDescuento());
		clienteEntity.setPassword(clienteDTO.getPasswd());
		clienteEntity.setRole(clienteDTO.getRole());
		
		if (clienteDao.updateCliente(clienteEntity)) {
			updateOk = true;
		}
		
		return updateOk;
	}

	@Override
	public boolean deleteCliente(int id) {
		
		boolean deleteOk = false;
		
		if (clienteDao.deleteCliente(id)) {
			deleteOk = true;
		}
		
		return deleteOk;
	}
	
}	
