package com.javasampleapproach.jdbcpostgresql.model;

public class Pago {
	private int id_tarjeta;
	private double valorAPagar;
	public int getId_tarjeta() {
		return id_tarjeta;
	}
	public void setId_tarjeta(int id_tarjeta) {
		this.id_tarjeta = id_tarjeta;
	}
	public double getValorAPagar() {
		return valorAPagar;
	}
	public void setValorAPagar(double valorAPagar) {
		this.valorAPagar = valorAPagar;
	}
	
}
