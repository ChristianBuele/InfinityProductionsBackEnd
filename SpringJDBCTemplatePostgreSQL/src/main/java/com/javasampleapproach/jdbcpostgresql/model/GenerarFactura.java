package com.javasampleapproach.jdbcpostgresql.model;

import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.javasampleapproach.jdbcpostgresql.service.CustomerService;
import com.javasampleapproach.jdbcpostgresql.util.ListarEventosPdf;

public class GenerarFactura extends Thread {
	List<carritoDetallado> listaProductos;
	List<carritoDetallado> listaPresets;
	String correo;
	

	public GenerarFactura(List<carritoDetallado> listaProductos, List<carritoDetallado> listaPresets,String correo) {
		super();
		this.listaProductos = listaProductos;
		this.listaPresets = listaPresets;
		this.correo=correo;
	}



	public void run() {
		System.out.println("Generando factura");
	    ListarEventosPdf x=new ListarEventosPdf();
	    String a=x.factura(listaProductos, listaPresets);
	    mail m=new mail(correo,a);
	    m.sendCorreo();
	    System.out.print("PDF GUARDADO");
	}

}
