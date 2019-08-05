package com.capgemini.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.daos.ClienteDAO;
import com.capgemini.dtos.ClienteDTO;
import com.capgemini.entities.ClienteEntity;
import com.capgemini.services.impls.IClienteService;

@Service
public class ClienteServiceImp implements IClienteService {
	
	@Autowired
	ClienteDAO clienteDao;
	
	@Override
	public List<ClienteDTO> findAll() {
		List<ClienteEntity> listaClientesEntity = clienteDao.findAll();
		List<ClienteDTO> listClientesDTO = new ArrayList<>();

		
		for (ClienteEntity cliente : listaClientesEntity) {
			ClienteDTO clienteDTO = new ClienteDTO();
			clienteDTO.setCli_id(cliente.getCli_id());
			clienteDTO.setCli_dni(cliente.getCli_dni());
			clienteDTO.setCli_nombre(cliente.getCli_nombre());
			clienteDTO.setCli_apellido(cliente.getCli_apellido());
			clienteDTO.setCli_email(cliente.getCli_email());
			clienteDTO.setCli_direccion(cliente.getCli_direccion());
			clienteDTO.setCli_codigopostal(cliente.getCli_codigopostal());
			clienteDTO.setCli_ciudad(cliente.getCli_ciudad());
			clienteDTO.setCli_puntos_dto(cliente.getCli_puntos_dto());
			
			// Añadir consumo
			
			
			listClientesDTO.add(clienteDTO);
		}
		
		return listClientesDTO;
	}

	@Override
	public ClienteDTO findById(int id) {
		
		ClienteDTO clienteDTO = new ClienteDTO();
		ClienteEntity clienteEntity = clienteDao.findById(id);
		
		clienteDTO.setCli_id(clienteEntity.getCli_id());
		clienteDTO.setCli_dni(clienteEntity.getCli_dni());
		clienteDTO.setCli_nombre(clienteEntity.getCli_nombre());
		clienteDTO.setCli_apellido(clienteEntity.getCli_apellido());
		clienteDTO.setCli_email(clienteEntity.getCli_email());
		clienteDTO.setCli_direccion(clienteEntity.getCli_direccion());
		clienteDTO.setCli_codigopostal(clienteEntity.getCli_codigopostal());
		clienteDTO.setCli_ciudad(clienteEntity.getCli_ciudad());
		clienteDTO.setCli_puntos_dto(clienteEntity.getCli_puntos_dto());
		
		return clienteDTO;
		
	}

	@Override
	public boolean addCliente(ClienteDTO clienteDTO) {
		
		boolean addOk = false;
		
		ClienteEntity clienteEntity = new ClienteEntity();
		clienteEntity.setCli_id(clienteDTO.getCli_id());
		clienteEntity.setCli_dni(clienteDTO.getCli_dni());
		clienteEntity.setCli_nombre(clienteDTO.getCli_nombre());
		clienteEntity.setCli_apellido(clienteDTO.getCli_apellido());
		clienteEntity.setCli_email(clienteDTO.getCli_email());
		clienteEntity.setCli_direccion(clienteDTO.getCli_direccion());
		clienteEntity.setCli_codigopostal(clienteDTO.getCli_codigopostal());
		clienteEntity.setCli_ciudad(clienteDTO.getCli_ciudad());
		clienteEntity.setCli_puntos_dto(clienteDTO.getCli_puntos_dto());
		
		if (clienteDao.addCliente(clienteEntity)) {
			addOk = true;
		}
		
		return addOk;
		
	}

	@Override
	public boolean updateCliente(ClienteDTO clienteDTO) {
		
		boolean updateOk = false;
		
		ClienteEntity clienteEntity = new ClienteEntity();
		clienteEntity.setCli_id(clienteDTO.getCli_id());
		clienteEntity.setCli_dni(clienteDTO.getCli_dni());
		clienteEntity.setCli_nombre(clienteDTO.getCli_nombre());
		clienteEntity.setCli_apellido(clienteDTO.getCli_apellido());
		clienteEntity.setCli_email(clienteDTO.getCli_email());
		clienteEntity.setCli_direccion(clienteDTO.getCli_direccion());
		clienteEntity.setCli_codigopostal(clienteDTO.getCli_codigopostal());
		clienteEntity.setCli_ciudad(clienteDTO.getCli_ciudad());
		clienteEntity.setCli_puntos_dto(clienteDTO.getCli_puntos_dto());
		
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
