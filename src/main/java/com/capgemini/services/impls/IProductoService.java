package com.capgemini.services.impls;

import java.util.List;

import com.capgemini.dtos.ProductoDTO;

public interface IProductoService {
	
	List<ProductoDTO> findAll();

	ProductoDTO findProductoById(int id);

	boolean addProducto(ProductoDTO producto);

	boolean updateProducto(ProductoDTO producto);

	boolean deleteProducto(int id);
}
