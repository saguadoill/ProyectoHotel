package com.capgemini.services.impls;

import com.capgemini.dtos.AdminDTO;

public interface IAdminService {
	
	AdminDTO findByUsername(String username);

}
