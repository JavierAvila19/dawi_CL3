package com.example.demo.services;

import java.io.InputStream;
import java.util.List;

import com.example.demo.models.Producto;

public interface ProductoService {
	
	public List<Producto> getAllProductos();
	
	public InputStream getReportProductos() throws Exception;

}
