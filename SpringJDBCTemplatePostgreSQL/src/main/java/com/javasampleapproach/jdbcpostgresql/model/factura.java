package com.javasampleapproach.jdbcpostgresql.model;

public class factura {
private int id_factura;
private int id_usuario;
private String fecha_factura;
public factura(int id_factura, int id_usuario, String fecha_factura) {
	super();
	this.id_factura = id_factura;
	this.id_usuario = id_usuario;
	this.fecha_factura = fecha_factura;
}
public factura() {
	
}
public int getId_factura() {
	return id_factura;
}
public void setId_factura(int id_factura) {
	this.id_factura = id_factura;
}
public int getId_usuario() {
	return id_usuario;
}
public void setId_usuario(int id_usuario) {
	this.id_usuario = id_usuario;
}
public String getFecha_factura() {
	return fecha_factura;
}
public void setFecha_factura(String fecha_factura) {
	this.fecha_factura = fecha_factura;
}

}
