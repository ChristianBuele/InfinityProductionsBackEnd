package com.javasampleapproach.jdbcpostgresql.model;

public class presetcarrito {
	private int id_carrito;
	private int id_preset;
	private String fecha;
	private int id_usuario;

	public int getId_carrito() {
		return id_carrito;
	}
	public void setId_carrito(int id_carrito) {
		this.id_carrito = id_carrito;
	}
	public int getId_preset() {
		return id_preset;
	}
	public void setId_preset(int id_preset) {
		this.id_preset = id_preset;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public int getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	
}
