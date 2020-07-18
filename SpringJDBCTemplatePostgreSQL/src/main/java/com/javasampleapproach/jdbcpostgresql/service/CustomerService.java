package com.javasampleapproach.jdbcpostgresql.service;

import java.util.List;

import com.javasampleapproach.jdbcpostgresql.model.Customer;
import com.javasampleapproach.jdbcpostgresql.model.ImageModel;
import com.javasampleapproach.jdbcpostgresql.model.carrito;
import com.javasampleapproach.jdbcpostgresql.model.productos;
import com.javasampleapproach.jdbcpostgresql.model.tarjeta;
import com.javasampleapproach.jdbcpostgresql.model.usuario;

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
}
