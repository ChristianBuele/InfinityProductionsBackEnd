package com.javasampleapproach.jdbcpostgresql.service.impl;

import java.util.List;

import com.javasampleapproach.jdbcpostgresql.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javasampleapproach.jdbcpostgresql.dao.CustomerDao;
import com.javasampleapproach.jdbcpostgresql.service.CustomerService;


@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired CustomerDao customerDao;
	
	@Override
	public void insert(Customer cus) {
		customerDao.insert(cus);
	}
	
	
	@Override
	public void insertBatch(List<Customer> customers) {
		customerDao.inserBatch(customers);
	}	
	
	public List<Customer> loadAllCustomer(){
		List<Customer> listCust = customerDao.loadAllCustomer();
		for(Customer cus: listCust){
			System.out.println(cus.toString());
		}
		return listCust;
	}

	@Override
	public void getCustomerById(long cust_id) {
		Customer cust = customerDao.findCustomerById(cust_id);
		System.out.println(cust);
	}

	@Override
	public void getCustomerNameById(long cust_id) {
		String name = customerDao.findNameById(cust_id);
		System.out.println("Customer's name = " + name);
	}

	@Override
	public void getTotalNumerCustomer() {
		int totalNumberCustomer = customerDao.getTotalNumberCustomer();
		System.out.println("Total Number Customer is: " + totalNumberCustomer);
	}


	@Override
	public carrito insertarCarrito(carrito carrito) {
		carrito carritonuevo = customerDao.insertarCarrito(carrito);
		return carritonuevo;
	}


	@Override
	public int verMaxId() {
		int id=customerDao.verIdMaximo();
		return id;
	}


	@Override
	public usuario insertarUsuario(usuario usuario) {
		usuario us=customerDao.insertarUsuario(usuario);
		return us;
	}


	@Override
	public tarjeta ingresarTarjeta(tarjeta tarjeta) {
		tarjeta t=customerDao.ingresarTarjeta(tarjeta);
		return t;
	}


	@Override
	public productos ingresarProducto(productos producto) {
		productos x=customerDao.ingresarProducto(producto);
		return x;
	}


	@Override
	public void insertarImagen(ImageModel image) {
		customerDao.insertarImagen(image);
		
	}


	@Override
	public ImageModel cargarImagen(String id) {
		System.out.println("Ingresa a ver el id "+ id);
		ImageModel img=customerDao.cargarImagen(id);
		return img;
	}



	@Override
	public boolean existeUsuario(String correo) {
		
		return customerDao.existeUsuario(correo);
		
	}


	@Override
	public boolean contraseniaCorrecta(String correo,String contra) {
		return customerDao.contraseniaCorrecta(correo, contra);
	}


	@Override
	public factura insertarFactura(factura factura) {
		factura x=customerDao.insertarFactura(factura);
		return x;
	}

	@Override
	public venta insertarVenta(venta venta) {
		venta x=customerDao.insertarVenta(venta);
		return x;
	}
	@Override
	public carritoproducto insertarcarritoProducto(carritoproducto carritoproducto) {
		carritoproducto x=customerDao.insertarcarritoProducto(carritoproducto);
		return x;
	}


	@Override
	public boolean addProducto(productos producto) {
		return customerDao.addProducto(producto);
	}


	@Override
	public int getIdImagen() {
		return customerDao.getIdImagen();
	}


	@Override
	public List<productoDao> findAllProducts() {
		return customerDao.findAllProducts();
	}

}
