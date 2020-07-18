package com.javasampleapproach.jdbcpostgresql.model;

public class productos {
	private int id_producto;
	private double precio;
	private String nombre;
	private byte [] imagen;
	private String descripcion;
	private String categoria;
	public productos() {
		
	}
	public productos(int id_producto, double precio, String nombre, byte[] imagen, String descripcion,
			String categoria) {
		this.id_producto = id_producto;
		this.precio = precio;
		this.nombre = nombre;
		this.imagen = imagen;
		this.descripcion = descripcion;
		this.categoria = categoria;
	}
	public int getId_producto() {
		return id_producto;
	}
	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public byte[] getImagen() {
		return imagen;
	}
	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
}
