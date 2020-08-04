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
	
	

	public GenerarFactura(List<carritoDetallado> listaProductos, List<carritoDetallado> listaPresets) {
		super();
		this.listaProductos = listaProductos;
		this.listaPresets = listaPresets;
	}



	public void run() {
		System.out.println("Generando factura");
	    ListarEventosPdf x=new ListarEventosPdf();
	    ByteArrayInputStream c=x.factura(listaProductos, listaPresets);
	    System.out.print("PDF GUARDADO");
	}

}
