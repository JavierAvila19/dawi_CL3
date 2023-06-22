package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

public class ProductoReport {
	
	private List<Producto> productosList;
	
	public ProductoReport() {
		super();
		this.productosList = new ArrayList<>();
	}
	
	public List<Producto> getProductosList(){
		return productosList;
	}
	
	public void setProductosList(List<Producto> productosList) {
		this.productosList = productosList;
	}

}
