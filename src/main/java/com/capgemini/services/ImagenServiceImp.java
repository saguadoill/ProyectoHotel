package com.capgemini.services;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;

import com.capgemini.services.impls.IImagenService;
import com.mortennobel.imagescaling.ResampleFilters;
import com.mortennobel.imagescaling.ResampleOp;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ImagenServiceImp implements IImagenService {
	
	//th:src="'data:image/png;base64,'+${usuario.foto}"

	@Override
	public String loadImagen(String foto_url) {
		String fotoParaEnviar = "";
		try {
			BufferedImage fotoCodificada = ImageIO.read(new File(foto_url));
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ImageIO.write(fotoCodificada, "jpg", bos);
			byte[] imageBytes = bos.toByteArray();
			fotoParaEnviar = Base64.getEncoder().encodeToString(imageBytes);
			bos.close();
			return fotoParaEnviar;
		} catch (IOException e) {
	        log.error("Error al codificar foto");
			e.printStackTrace();
		}
		return fotoParaEnviar;
	}

	@Override
	public String saveImagen(String nombre, byte[] fotoBytes, String origen) {
		String foto_url = "";
		if (origen.equalsIgnoreCase("usuario")) {
			foto_url = "src/main/resources/static/usuarios/foto_user_" + nombre + ".png";
		} else if (origen.equalsIgnoreCase("apartamento")) {
			foto_url = "src/main/resources/fotos/apartamentos/foto_apart_" + nombre + ".png";
		}
		try {
			InputStream in = new ByteArrayInputStream(fotoBytes);
			BufferedImage fotoParaGuardar = ImageIO.read(in);
			fotoParaGuardar = resize(fotoParaGuardar, 100, 100);
			File outputfile = new File(foto_url);
			if (!outputfile.exists()) {
				if (outputfile.createNewFile()) {
					log.warn("Archivo creado de forma correcta al guardar imagen");
				} else {
					log.error("Ha habido un problema al crear el archivo para guardar la imagen");
				}
			}
			ImageIO.write(fotoParaGuardar, "png", outputfile);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return foto_url;
	}

	private BufferedImage resize(BufferedImage img, int newW, int newH) {
		ResampleOp resizeOp = new ResampleOp(newW, newH);
		resizeOp.setFilter(ResampleFilters.getLanczos3Filter());
		return resizeOp.filter(img, null);
	}

}
