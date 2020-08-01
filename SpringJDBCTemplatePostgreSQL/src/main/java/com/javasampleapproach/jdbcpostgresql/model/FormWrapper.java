package com.javasampleapproach.jdbcpostgresql.model;

import org.springframework.web.multipart.MultipartFile;

public class FormWrapper {
	private MultipartFile  imagenProducto;
    private String nombreProducto;
    private String desProducto;
    private double precioProducto;
    private String categoriaProducto;
	
	public MultipartFile getImagenProducto() {
		return imagenProducto;
	}
	public void setImagenProducto(MultipartFile imagenProducto) {
		this.imagenProducto = imagenProducto;
	}
	public String getNombreProducto() {
		return nombreProducto;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	public String getDesProducto() {
		return desProducto;
	}
	public void setDesProducto(String desProducto) {
		this.desProducto = desProducto;
	}
	public double getPrecioProducto() {
		return precioProducto;
	}
	public void setPrecioProducto(double precioProducto) {
		this.precioProducto = precioProducto;
	}
	public String getCategoriaProducto() {
		return categoriaProducto;
	}
	public void setCategoriaProducto(String categoriaProducto) {
		this.categoriaProducto = categoriaProducto;
	}
	
    
}
