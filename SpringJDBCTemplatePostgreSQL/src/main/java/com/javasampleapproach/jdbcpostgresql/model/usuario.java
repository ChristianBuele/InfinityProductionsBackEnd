package com.javasampleapproach.jdbcpostgresql.model;

public class usuario {
private int id_usuario;
private int id_carrito;
private String nombre_usuario;
private String apellido_usuario;
private String correo_usuario;
private String contrasenia_usuario;
private String rol;
public usuario() {
	
}
public usuario(int id_usuario, int id_carrito, String nombre_usuario, String apellido_usuario, String correo_usuario,
		String contrasenia_usuario, String rol) {
	this.id_usuario = id_usuario;
	this.id_carrito = id_carrito;
	this.nombre_usuario = nombre_usuario;
	this.apellido_usuario = apellido_usuario;
	this.correo_usuario = correo_usuario;
	this.contrasenia_usuario = contrasenia_usuario;
	this.rol = rol;
}
public int getId_usuario() {
	return id_usuario;
}
public void setId_usuario(int id_usuario) {
	this.id_usuario = id_usuario;
}
public int getId_carrito() {
	return id_carrito;
}
public void setId_carrito(int id_carrito) {
	this.id_carrito = id_carrito;
}
public String getNombre_usuario() {
	return nombre_usuario;
}
public void setNombre_usuario(String nombre_usuario) {
	this.nombre_usuario = nombre_usuario;
}
public String getApellido_usuario() {
	return apellido_usuario;
}
public void setApellido_usuario(String apellido_usuario) {
	this.apellido_usuario = apellido_usuario;
}
public String getCorreo_usuario() {
	return correo_usuario;
}
public void setCorreo_usuario(String correo_usuario) {
	this.correo_usuario = correo_usuario;
}
public String getContrasenia_usuario() {
	return contrasenia_usuario;
}
public void setContrasenia_usuario(String contrasenia_usuario) {
	this.contrasenia_usuario = contrasenia_usuario;
}
public String getRol() {
	return rol;
}
public void setRol(String rol) {
	this.rol = rol;
}
@Override
public String toString() {
	return "US:"+this.nombre_usuario+" Correo:"+correo_usuario+" PASS:"+this.contrasenia_usuario;
}
}
