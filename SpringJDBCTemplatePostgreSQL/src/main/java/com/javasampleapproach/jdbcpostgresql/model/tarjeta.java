package com.javasampleapproach.jdbcpostgresql.model;

public class tarjeta {
private int id_tarjeta;
private int id_usuariot;
private String num_tarjeta;
private String nombre_tarjeta;
private int mes_expiracion;
private int anio_expiracion;
private int ccv_tarjeta;
public tarjeta(int id_tarjeta, int id_usuariot, String num_tarjeta, String nombre_tarjeta, int mes_expiracion,
		int anio_expiracion, int ccv_tarjeta) {
	this.id_tarjeta = id_tarjeta;
	this.id_usuariot = id_usuariot;
	this.num_tarjeta = num_tarjeta;
	this.nombre_tarjeta = nombre_tarjeta;
	this.mes_expiracion = mes_expiracion;
	this.anio_expiracion = anio_expiracion;
	this.ccv_tarjeta = ccv_tarjeta;
}
public tarjeta() {
	
}
public int getId_tarjeta() {
	return id_tarjeta;
}
public void setId_tarjeta(int id_tarjeta) {
	this.id_tarjeta = id_tarjeta;
}
public int getId_usuariot() {
	return id_usuariot;
}
public void setId_usuariot(int id_usuariot) {
	this.id_usuariot = id_usuariot;
}
public String getNum_tarjeta() {
	return num_tarjeta;
}
public void setNum_tarjeta(String num_tarjeta) {
	this.num_tarjeta = num_tarjeta;
}
public String getNombre_tarjeta() {
	return nombre_tarjeta;
}
public void setNombre_tarjeta(String nombre_tarjeta) {
	this.nombre_tarjeta = nombre_tarjeta;
}
public int getMes_expiracion() {
	return mes_expiracion;
}
public void setMes_expiracion(int mes_expiracion) {
	this.mes_expiracion = mes_expiracion;
}
public int getAnio_expiracion() {
	return anio_expiracion;
}
public void setAnio_expiracion(int anio_expiracion) {
	this.anio_expiracion = anio_expiracion;
}
public int getCcv_tarjeta() {
	return ccv_tarjeta;
}
public void setCcv_tarjeta(int ccv_tarjeta) {
	this.ccv_tarjeta = ccv_tarjeta;
}


}
