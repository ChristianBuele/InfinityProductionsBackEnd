package com.javasampleapproach.jdbcpostgresql.model;

public class venta {
private int id_venta;
private int id_factura;
private int id_carrito;
private String fecha_venta;
private String fecha_evento;
private String direccion_evento;
private String direccion_Entrega;
private double precio_final;
public venta() {
	
}
public venta(int id_venta, int id_factura, int id_carrito, String fecha_venta, String fecha_evento,
		String direccion_evento, String direccion_Entrega, double precio_final) {
	super();
	this.id_venta = id_venta;
	this.id_factura = id_factura;
	this.id_carrito = id_carrito;
	this.fecha_venta = fecha_venta;
	this.fecha_evento = fecha_evento;
	this.direccion_evento = direccion_evento;
	this.direccion_Entrega = direccion_Entrega;
	this.precio_final = precio_final;
}
public int getId_venta() {
	return id_venta;
}
public void setId_venta(int id_venta) {
	this.id_venta = id_venta;
}
public int getId_factura() {
	return id_factura;
}
public void setId_factura(int id_factura) {
	this.id_factura = id_factura;
}
public int getId_carrito() {
	return id_carrito;
}
public void setId_carrito(int id_carrito) {
	this.id_carrito = id_carrito;
}
public String getFecha_venta() {
	return fecha_venta;
}
public void setFecha_venta(String fecha_venta) {
	this.fecha_venta = fecha_venta;
}
public String getFecha_evento() {
	return fecha_evento;
}
public void setFecha_evento(String fecha_evento) {
	this.fecha_evento = fecha_evento;
}
public String getDireccion_evento() {
	return direccion_evento;
}
public void setDireccion_evento(String direccion_evento) {
	this.direccion_evento = direccion_evento;
}
public String getDireccion_Entrega() {
	return direccion_Entrega;
}
public void setDireccion_Entrega(String direccion_Entrega) {
	this.direccion_Entrega = direccion_Entrega;
}
public double getPrecio_final() {
	return precio_final;
}
public void setPrecio_final(double precio_final) {
	this.precio_final = precio_final;
}

}
