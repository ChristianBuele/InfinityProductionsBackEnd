package com.javasampleapproach.jdbcpostgresql.model;

import javax.mail.Multipart;

import org.springframework.web.multipart.MultipartFile;

public class presets {
private int id_preset;
private String nombrePreset;
private MultipartFile imagenPreset;
private byte[] bytesImagen;
private byte[] bytesPreset;
private String descripcionPreset;
private MultipartFile preset;
private String categoriaPreset;
private double precioPreset;
public String getNombrePreset() {
	return nombrePreset;
	
}
public void setNombrePreset(String nombrePreset) {
	this.nombrePreset = nombrePreset;
}
public MultipartFile getImagenPreset() {
	return imagenPreset;
}
public void setImagenPreset(MultipartFile imagenPreset) {
	this.imagenPreset = imagenPreset;
}
public String getDescripcionPreset() {
	return descripcionPreset;
}
public void setDescripcionPreset(String descripcionPreset) {
	this.descripcionPreset = descripcionPreset;
}
public MultipartFile getPreset() {
	return preset;
}
public void setPreset(MultipartFile preset) {
	this.preset = preset;
}
public String getCategoriaPreset() {
	return categoriaPreset;
}
public void setCategoriaPreset(String categoriaPreset) {
	this.categoriaPreset = categoriaPreset;
}
public double getPrecioPreset() {
	return precioPreset;
}
public void setPrecioPreset(double precioPreset) {
	this.precioPreset = precioPreset;
}
public int getId_preset() {
	return id_preset;
}
public void setId_preset(int id_preset) {
	this.id_preset = id_preset;
}
public byte[] getBytesImagen() {
	return bytesImagen;
}
public void setBytesImagen(byte[] bytesImagen) {
	this.bytesImagen = bytesImagen;
}
public byte[] getBytesPreset() {
	return bytesPreset;
}
public void setBytesPreset(byte[] bytesPreset) {
	this.bytesPreset = bytesPreset;
}


}
