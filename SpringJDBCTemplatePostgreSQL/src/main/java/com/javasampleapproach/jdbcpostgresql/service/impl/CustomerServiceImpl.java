package com.javasampleapproach.jdbcpostgresql.service.impl;

import java.util.ArrayList;
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
	public List<presets> getPreset(ArrayList<String> nombres){
		return customerDao.getPreset(nombres);
	}

	@Override
	public List<tarjeta> listAllTarjeta(int id) {
		return customerDao.findTarjeta(id);
	}

	@Override
	public List<eventosDao> listarEventos() {
		return customerDao.listarEventos();
	}

	@Override
	public List<facturaDao> listarFacturas(int id) {
		return customerDao.listarFacturas(id);
	}

	@Override
	public List<preventa> listarpreventa(int id_usuario, int id_tarjeta) {
		return customerDao.listarPreventas(id_usuario,id_tarjeta);
	}

	@Override
	public void eliminarProducto(int id) {
		customerDao.eliminarProducto(id);
	}

	@Override
	public usuario datosUsuario(int id) {
		return customerDao.datosUsuario(id);
	}

	@Override
	public void actualizarDataUsuario(String nom, String ape, String contra, int id) {
		customerDao.actualizarDataUsuario(nom,ape,contra,id);
	}

	@Override
	public List<carritoproductoDao> listarProCarri(int id) {
		return customerDao.listarProCarri(id);
	}

	@Override
	public int idCarrito(int id) {
		return customerDao.idCarrito(id);
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
	public usuario existeUsuario(String correo) {
		
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

	@Override
	public List<productos> listarProductos() {
		return customerDao.listarProductos();
	}

	@Override
	public int getIdUsuario(String correo) {
		return customerDao.getIdUsuario(correo);
	}

	@Override
	public boolean addPreset(presets preset) {
		return customerDao.addPreset(preset);
	}
	@Override
	public List<presets> findPremiumPresets() {
		return customerDao.findPremiumPresets();
	}
	@Override
	public List<presets> findFreePresets() {
		// TODO Auto-generated method stub
		return customerDao.findFreePresets();
	}
	@Override
	public boolean pagarFactura(int id_tarjeta, double nuevo_valor) {
		// TODO Auto-generated method stub
		return customerDao.pagarFactura(id_tarjeta, nuevo_valor);
	}
	@Override
	public double saldoTarjeta(int id) {
		// TODO Auto-generated method stub
		return customerDao.saldoTarjeta(id);
	}
	@Override
	public usuario getDatosUsuario(int id) {
		return customerDao.getDatosUsuario(id);
	}
	@Override
	public int getIdFactura() {
		// TODO Auto-generated method stub
		return customerDao.getIdFactura();
	}
	@Override
	public List<carritoproducto> getProductoCarrito(int idCarrito) {
		// TODO Auto-generated method stub
		return customerDao.getProductoCarrito(idCarrito);
	}
	@Override
	public List<carritoDetallado> getCarritoDetalladoProductos(int idCarrito) {
		return customerDao.getCarritoDetalladoProductos(idCarrito);
	}
	@Override
	public List<carritoDetallado> getCarritoDetalladoPresets(int idCarrito) {
	return customerDao.getCarritoDetalladoPresets(idCarrito);
	}
	@Override
	public boolean addValorCarrito(int id, double valor) {
		return customerDao.addValorCarrito(id, valor);
	}
	@Override
	public boolean actualizarIdCarrito(int id_usuario, int id_Carrito) {
		return customerDao.actualizarIdCarrito(id_usuario, id_Carrito);
	}

	@Override
	public void hola() {

	}
	@Override
	public String getCorreoUsuario(int id) {
		return customerDao.getCorreoUsuario(id);
	}

	@Override
	public void eliminarproductocarrito(int id) {
		customerDao.eliminarproductocarrito(id);
	}
	@Override
	public boolean addPresetCarrito(presetcarrito preset) {
		// TODO Auto-generated method stub
		return customerDao.addPresetCarrito(preset);
	}
	@Override
	public boolean eliminarPresetCarrito(int id) {
		// TODO Auto-generated method stub
		return customerDao.eliminarPresetCarrito(id);
	}
	@Override
	public boolean eliminarTarjeta(int id) {
		// TODO Auto-generated method stub
		return customerDao.eliminarTarjeta(id);
	}
	@Override
	public List<usuario> cargarUsuario() {
		// TODO Auto-generated method stub
		return customerDao.cargarUsuario();
	}
	@Override
	public boolean actualizarEstadoUsuario(int id, String estado) {
		// TODO Auto-generated method stub
		return customerDao.actualizarEstadoUsuario(id, estado);
	}

}
