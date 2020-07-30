package com.javasampleapproach.jdbcpostgresql.dao;

import java.util.List;
import com.javasampleapproach.jdbcpostgresql.model.Customer;
import com.javasampleapproach.jdbcpostgresql.model.ImageModel;
import com.javasampleapproach.jdbcpostgresql.model.carrito;
import com.javasampleapproach.jdbcpostgresql.model.productos;
import com.javasampleapproach.jdbcpostgresql.model.tarjeta;
import com.javasampleapproach.jdbcpostgresql.model.usuario;

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
	boolean contraseniaCorrecta(String correo,String contra);

}
