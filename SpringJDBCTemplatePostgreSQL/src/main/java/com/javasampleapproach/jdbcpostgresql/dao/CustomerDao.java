package com.javasampleapproach.jdbcpostgresql.dao;

import java.util.ArrayList;
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
	boolean contraseniaCorrecta(String correo,String contra);
	factura insertarFactura(factura factura);
	venta insertarVenta(venta venta);
	carritoproducto insertarcarritoProducto(carritoproducto carritoproducto);
	boolean addProducto(productos producto);
	int getIdImagen();
	List<productoDao> findAllProducts();
	int getIdUsuario(String correo);
	boolean addPreset(presets preset);
	List<presets> getPreset(ArrayList<String> ids);
	List<presets> findPremiumPresets();
	List<presets> findFreePresets();
	boolean pagarFactura(int id_tarjeta,double nuevo_valor); 
	List<tarjeta> findTarjeta(int id);
	List<eventosDao> listarEventos();
	List<facturaDao> listarFacturas(int id);
	double saldoTarjeta(int id);
	usuario getDatosUsuario(int id);
	int getIdFactura();
	List<preventa> listarPreventas(int id_usuario,int id_tarjeta);
	void eliminarProducto(int id);
	}
