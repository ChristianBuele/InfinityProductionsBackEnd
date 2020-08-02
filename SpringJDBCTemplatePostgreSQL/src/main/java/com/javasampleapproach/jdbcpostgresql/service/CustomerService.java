package com.javasampleapproach.jdbcpostgresql.service;

import java.util.ArrayList;
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
	boolean existeUsuario(String correo);
	boolean contraseniaCorrecta(String correo,String contra);
	factura insertarFactura(factura factura);
	venta insertarVenta(venta venta);
	carritoproducto insertarcarritoProducto(carritoproducto carritoproducto);
	boolean addProducto(productos producto);
	int getIdImagen();
	List<productoDao> findAllProducts();
	int getIdUsuario(String correo);
	boolean addPreset(presets preset);
	List<presets> getPreset(ArrayList<String> nombres);
	List<tarjeta> listAllTarjeta(int id);
	List<eventosDao> listarEventos();
	List<facturaDao> listarFacturas(int id);
}
