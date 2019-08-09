package com.capgemini.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.daos.AdminDAO;
import com.capgemini.dtos.AdminDTO;
import com.capgemini.entities.AdminEntity;
import com.capgemini.services.impls.IAdminService;

@Service
public class AdminServiceImp implements IAdminService {
	
	@Autowired
	AdminDAO adminDao;

	@Override
	public AdminDTO findByUsername(String username) {
		
		AdminDTO adminDTO = new AdminDTO();
	    AdminEntity adminEntity= adminDao.findByUsername(username);
	    
	    adminDTO.setId(adminEntity.getId());
	    adminDTO.setNombre(adminEntity.getNombre());
	    adminDTO.setRole(adminEntity.getNombre());
	    adminDTO.setPasswd(adminEntity.getPasswd());
		
		return adminDTO;
	}
	
	
	
}
