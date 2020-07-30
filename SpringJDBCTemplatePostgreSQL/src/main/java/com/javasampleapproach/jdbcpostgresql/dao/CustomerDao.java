package com.javasampleapproach.jdbcpostgresql.dao;

import java.util.List;

import com.javasampleapproach.jdbcpostgresql.model.*;

public interface CustomerDao {
	void insert(Customer cus);
	void inserBatch(List<Customer> customers);
	List<Customer> loadAllCustomer();
	Customer findCustomerById(long cust_id);
	String findNameById(long cust_id);
	int getTotalNumberCustomer();
	carrito insertarCarrito(carrito carrito);
	int verIdMaximo();
	usuario insertarUsuario(usuario usuario);
	tarjeta ingresarTarjeta(tarjeta tarjeta);
	productos ingresarProducto(productos producto);
	void insertarImagen(ImageModel image);
	boolean existeUsuario(String correo);
	ImageModel cargarImagen(String id);
<<<<<<< HEAD
	boolean contraseniaCorrecta(String correo,String contra);

=======
	factura insertarFactura(factura factura);
	venta insertarVenta(venta venta);
	carritoproducto insertarcarritoProducto(carritoproducto carritoproducto);
>>>>>>> 378f9a0a152dffcdd423fb9ffcaa692e17a5686a
}
