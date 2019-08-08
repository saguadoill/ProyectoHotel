package com.capgemini.daos;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.capgemini.entities.ClienteEntity;

@Mapper
public interface ClienteDAO {
		
	List<ClienteEntity> findAll();
	
	ClienteEntity findById(int id);
	
	ClienteEntity findCliente(String email);
	
	boolean addCliente(ClienteEntity cliente);
	
	boolean updateCliente(ClienteEntity cliente);
	
	boolean deleteCliente(int id);
	
	
	
		
}

