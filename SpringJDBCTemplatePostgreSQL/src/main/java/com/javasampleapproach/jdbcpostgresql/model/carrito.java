package com.javasampleapproach.jdbcpostgresql.model;

public class carrito {
private int id_carrito;
private double total;
public carrito() {
	
}
public carrito(int id_carrito, double total) {
	this.id_carrito = id_carrito;
	this.total = total;
}
public int getId_carrito() {
	return id_carrito;
}
public void setId_carrito(int id_carrito) {
	this.id_carrito = id_carrito;
}
public double getTotal() {
	return total;
}
public void setTotal(double total) {
	this.total = total;
}


}
