package com.javasampleapproach.jdbcpostgresql.model;

public class carritoproducto {
private int id_productocarrito;
private int id_carrito;
private int id_producto;
private String fecha;
private int cantidad;
public carritoproducto() {
	
}
public carritoproducto(int id_productocarrito, int id_carrito, int id_producto, String fecha) {
	this.id_productocarrito = id_productocarrito;
	this.id_carrito = id_carrito;
	this.id_producto = id_producto;
	this.fecha = fecha;
}
public int getId_productocarrito() {
	return id_productocarrito;
}
public void setId_productocarrito(int id_productocarrito) {
	this.id_productocarrito = id_productocarrito;
}
public int getId_carrito() {
	return id_carrito;
}
public void setId_carrito(int id_carrito) {
	this.id_carrito = id_carrito;
}
public int getId_producto() {
	return id_producto;
}
public void setId_producto(int id_producto) {
	this.id_producto = id_producto;
}
public String getFecha() {
	return fecha;
}
public void setFecha(String fecha) {
	this.fecha = fecha;
}

public int getCantidad() {
	return cantidad;
}
public void setCantidad(int cantidad) {
	this.cantidad = cantidad;
}


}
