package com.javasampleapproach.jdbcpostgresql.service;

import java.util.List;

import com.javasampleapproach.jdbcpostgresql.model.*;

public interface CustomerService {
	void insert(Customer cus);
	void insertBatch(List<Customer> customers);
	List<Customer> loadAllCustomer();
	void getCustomerById(long cust_id);
	void getCustomerNameById(long cust_id);
	void getTotalNumerCustomer();
	public carrito insertarCarrito(carrito carrito);
	int verMaxId();
	usuario insertarUsuario(usuario usuario);
	tarjeta ingresarTarjeta (tarjeta tarjeta);
	productos ingresarProducto(productos producto);
	void insertarImagen(ImageModel image);
	ImageModel cargarImagen(String id);
<<<<<<< HEAD
	boolean existeUsuario(String correo);
	boolean contraseniaCorrecta(String correo,String contra);
=======
	factura insertarFactura(factura factura);
	venta insertarVenta(venta venta);
	carritoproducto insertarcarritoProducto(carritoproducto carritoproducto);
>>>>>>> 378f9a0a152dffcdd423fb9ffcaa692e17a5686a
}
