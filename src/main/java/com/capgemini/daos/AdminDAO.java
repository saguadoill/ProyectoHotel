package com.capgemini.daos;

import org.apache.ibatis.annotations.Mapper;

import com.capgemini.entities.AdminEntity;

@Mapper
public interface AdminDAO {

	AdminEntity findByUsername(String username);
}
