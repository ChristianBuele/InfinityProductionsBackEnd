package com.javasampleapproach.jdbcpostgresql.model;

import java.io.Serializable;

public class ImageModel implements Serializable {
	private Long id;

	private String name;
	private int id_imagen;
	private String type;
	byte[] picByte;
	public ImageModel() {
		super();
	}
	public ImageModel(String name, String type, byte[] picByte) {
		this.name = name;
		this.type = type;
		this.picByte = picByte;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public byte[] getPicByte() {
		return picByte;
	}
	public void setPicByte(byte[] picByte) {
		this.picByte = picByte;
	}
	public int getId_imagen() {
		return id_imagen;
	}
	public void setId_imagen(int id_imagen) {
		this.id_imagen = id_imagen;
	}
	
	
}
