package com.javasampleapproach.jdbcpostgresql.model;

import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.javasampleapproach.jdbcpostgresql.service.CustomerService;
import com.javasampleapproach.jdbcpostgresql.util.ListarEventosPdf;

public class GenerarFactura extends Thread {
	List<carritoDetallado> listaProductos;
	List<carritoDetallado> listaPresets;
	String correo;
	String nomArchivo;
	 @Autowired
	 private JavaMailSender sender;
	public GenerarFactura(List<carritoDetallado> listaProductos, List<carritoDetallado> listaPresets,String correo) {
		super();
		this.listaProductos = listaProductos;
		this.listaPresets = listaPresets;
		this.correo=correo;
	}



	public void run() {
		System.out.println("Generando factura");
	    ListarEventosPdf x=new ListarEventosPdf();
	    nomArchivo=x.factura(listaProductos, listaPresets);
	    mail m=new mail(correo,nomArchivo);
	   m.sendCorreo();
	    System.out.print("PDF GUARDADO");
	}

	

	public List<carritoDetallado> getListaProductos() {
		return listaProductos;
	}



	public void setListaProductos(List<carritoDetallado> listaProductos) {
		this.listaProductos = listaProductos;
	}



	public List<carritoDetallado> getListaPresets() {
		return listaPresets;
	}



	public void setListaPresets(List<carritoDetallado> listaPresets) {
		this.listaPresets = listaPresets;
	}



	public String getCorreo() {
		return correo;
	}



	public void setCorreo(String correo) {
		this.correo = correo;
	}



	public String getNomArchivo() {
		return nomArchivo;
	}



	public void setNomArchivo(String nomArchivo) {
		this.nomArchivo = nomArchivo;
	}

}
