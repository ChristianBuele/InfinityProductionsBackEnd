package com.javasampleapproach.jdbcpostgresql.model;

public class carritoDetallado {
	private String nombreProducto;
	private double precioProducto;
	private int idCarrito;
	private String descripcion;
	private byte[] imagen;
	private int idCarritoPre;
	public String getNombreProducto() {
		return nombreProducto;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	public double getPrecioProducto() {
		return precioProducto;
	}
	public void setPrecioProducto(double precioProducto) {
		this.precioProducto = precioProducto;
	}
	public int getIdCarrito() {
		return idCarrito;
	}
	public void setIdCarrito(int idCarrito) {
		this.idCarrito = idCarrito;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public byte[] getImagen() {
		return imagen;
	}
	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}
	public int getIdCarritoPre() {
		return idCarritoPre;
	}
	public void setIdCarritoPre(int idCarritoPre) {
		this.idCarritoPre = idCarritoPre;
	}
	
	
}
