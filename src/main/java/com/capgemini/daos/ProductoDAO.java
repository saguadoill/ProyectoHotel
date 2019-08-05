package com.capgemini.daos;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.capgemini.entities.ProductoEntity;

@Mapper
public interface ProductoDAO {
	
	List<ProductoEntity> findAll();
	
	ProductoEntity findProductoById(int id);
	
	boolean addProducto(ProductoEntity producto);
	
	boolean updateProducto(ProductoEntity producto);
	
	boolean deleteProducto(int id);
}
