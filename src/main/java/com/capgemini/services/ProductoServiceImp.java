package com.capgemini.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.daos.ProductoDAO;
import com.capgemini.dtos.ProductoDTO;
import com.capgemini.entities.ProductoEntity;
import com.capgemini.services.impls.IProductoService;

@Service
public class ProductoServiceImp implements IProductoService{
	
	@Autowired
	ProductoDAO productoDao;

	@Override
	public List<ProductoDTO> findAll() {
		List<ProductoEntity> listaProductosEntity = new ArrayList<>();
		List<ProductoDTO> listaProductosDto = new ArrayList<>();

		listaProductosEntity = productoDao.findAll();

		for (ProductoEntity producto : listaProductosEntity) {
			ProductoDTO productoDto = new ProductoDTO();

			productoDto.setId(producto.getId());
			productoDto.setDescripcion(producto.getDescripcion());
			productoDto.setPrecio(producto.getPrecio());

			listaProductosDto.add(productoDto);
		}
		return listaProductosDto;
	}

	@Override
	public ProductoDTO findProductoById(int id) {
		ProductoEntity producto = productoDao.findProductoById(id);
		ProductoDTO productoDto = new ProductoDTO();

		productoDto.setId(producto.getId());
		productoDto.setDescripcion(producto.getDescripcion());
		productoDto.setPrecio(producto.getPrecio());

		return productoDto;
	}

	@Override
	public boolean addProducto(ProductoDTO producto) {
		boolean addOk = false;
		ProductoEntity productoEntity = new ProductoEntity();
		
		productoEntity.setDescripcion(producto.getDescripcion());
		productoEntity.setPrecio(producto.getPrecio());

		if (productoDao.addProducto(productoEntity)) {
			addOk = true;
		}
		return addOk;
	}

	@Override
	public boolean updateProducto(ProductoDTO producto) {
		boolean updateOk = false;
		ProductoEntity productoEntity = productoDao.findProductoById(producto.getId());
		
		productoEntity.setDescripcion(producto.getDescripcion());
		productoEntity.setPrecio(producto.getPrecio());

		if (productoDao.updateProducto(productoEntity)) {
			updateOk = true;
		}
		return updateOk;
	}

	@Override
	public boolean deleteProducto(int id) {
		boolean deleteOk = false;

		if (productoDao.deleteProducto(id)) {
			deleteOk = true;
		}

		return deleteOk;
	}

}
