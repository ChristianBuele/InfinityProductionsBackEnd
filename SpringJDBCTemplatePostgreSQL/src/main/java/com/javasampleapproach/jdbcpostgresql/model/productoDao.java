package com.javasampleapproach.jdbcpostgresql.model;

public class productoDao {
	private int id_imagen;
	private String nombre;
    private String descripcion;
    private double precio;
    private String categoria;
	public int getImagen() {
		return id_imagen;
	}
	public void setImagen(int imagen) {
		this.id_imagen = imagen;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
    
}
