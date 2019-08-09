package com.capgemini.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.dtos.AdminDTO;
import com.capgemini.services.impls.IAdminService;

@RestController
public class AdminController {

	@Autowired
	IAdminService adminService;
	
	@RequestMapping(value = "/admin/{username}", method = RequestMethod.GET)
	public AdminDTO buscarAdminPorUsername(@PathVariable(name = "username") String username) {
		return adminService.findByUsername(username);
	}
	
}
