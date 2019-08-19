package com.capgemini.services.impls;

public interface IImagenService {
	
	public String loadImagen(String foto_url);
	
	 public String saveImagen(String nombre, byte[] fotoBytes, String origen);

}
