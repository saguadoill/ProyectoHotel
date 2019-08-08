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

		
		for (ClienteEntity clienteEntity : listaClientesEntity) {
			ClienteDTO clienteDTO = new ClienteDTO();
			
			clienteDTO.setId(clienteEntity.getId());
			clienteDTO.setDni(clienteEntity.getDni());
			clienteDTO.setNombre(clienteEntity.getNombre());
			clienteDTO.setApellido(clienteEntity.getApellido());
			clienteDTO.setEmail(clienteEntity.getEmail());
			clienteDTO.setDireccion(clienteEntity.getDireccion());
			clienteDTO.setCodigoPostal(clienteEntity.getCodigoPostal());
			clienteDTO.setCiudad(clienteEntity.getCiudad());
			clienteDTO.setPuntosDescuento(clienteEntity.getPuntosDescuento());
		
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

	
	@Override
	public ClienteDTO findCliente(String email, String password) {
		
		ClienteDTO clienteDTO = new ClienteDTO();
		
		ClienteEntity clienteEntity = clienteDao.findCliente(email);
		
		System.out.println(clienteEntity);
		
		if (clienteEntity.getPassword().equals(password)) {
			
			clienteDTO.setId(clienteEntity.getId());
			clienteDTO.setDni(clienteEntity.getDni());
			clienteDTO.setNombre(clienteEntity.getNombre());
			clienteDTO.setApellido(clienteEntity.getApellido());
			clienteDTO.setEmail(clienteEntity.getEmail());
			clienteDTO.setDireccion(clienteEntity.getDireccion());
			clienteDTO.setCodigoPostal(clienteEntity.getCodigoPostal());
			clienteDTO.setCiudad(clienteEntity.getCiudad());
			clienteDTO.setPuntosDescuento(clienteEntity.getPuntosDescuento());
				
		}
		
		return clienteDTO;
	}
	
}	
