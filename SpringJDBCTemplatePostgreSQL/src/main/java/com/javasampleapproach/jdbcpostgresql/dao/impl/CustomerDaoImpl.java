package com.javasampleapproach.jdbcpostgresql.dao.impl;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import com.javasampleapproach.jdbcpostgresql.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.javasampleapproach.jdbcpostgresql.dao.CustomerDao;

@Repository
public class CustomerDaoImpl extends JdbcDaoSupport implements CustomerDao, Serializable{
	
	@Autowired 
	DataSource dataSource;
	
	@PostConstruct
	private void initialize(){
		setDataSource(dataSource);
	}
	
	@Override
	public void insert(Customer cus) {
		String sql = "INSERT INTO customer " +
				"(CUST_ID, NAME, AGE) VALUES (?, ?, ?)" ;
		getJdbcTemplate().update(sql, new Object[]{
				cus.getCustId(), cus.getName(), cus.getAge()
		});
	}
	
	@Override
	public void inserBatch(List<Customer> customers) {
		String sql = "INSERT INTO customer " + "(CUST_ID, NAME, AGE) VALUES (?, ?, ?)";
		getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Customer customer = customers.get(i);
				ps.setLong(1, customer.getCustId());
				ps.setString(2, customer.getName());
				ps.setInt(3, customer.getAge());
			}
			
			public int getBatchSize() {
				return customers.size();
			}
		});

	}
	
	public List<Customer> loadAllCustomer(){
		String sql = "SELECT * FROM customer";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
		
		List<Customer> result = new ArrayList<Customer>();
		for(Map<String, Object> row:rows){
			Customer cus = new Customer();
			cus.setCustId((Long)row.get("cust_id"));
			cus.setName((String)row.get("name"));
			cus.setAge((Integer) row.get("age"));
			result.add(cus);
		}
		
		return result;
	}

	@Override
	public Customer findCustomerById(long cust_id) {
		String sql = "SELECT * FROM customer WHERE CUST_ID = ?";
		return (Customer)getJdbcTemplate().queryForObject(sql, new Object[]{cust_id}, new RowMapper<Customer>(){
			@Override
			public Customer mapRow(ResultSet rs, int rwNumber) throws SQLException {
				Customer cust = new Customer();
				cust.setCustId(rs.getLong("cust_id"));
				cust.setName(rs.getString("name"));
				cust.setAge(rs.getInt("age"));
				return cust;
			}
		});
	}

	@Override
	public String findNameById(long cust_id) {
		String sql = "SELECT name FROM customer WHERE cust_id = ?";
		return getJdbcTemplate().queryForObject(sql, new Object[]{cust_id}, String.class);
	}

	@Override
	public int getTotalNumberCustomer() {
		String sql = "SELECT Count(*) FROM customer";
		int total = getJdbcTemplate().queryForObject(sql, Integer.class);
		return total;
	}
	@Override
	public int verIdMaximo() {
		String sql = "SELECT MAX(id_carrito) FROM carrito";
		
		int total = getJdbcTemplate().queryForObject(sql, Integer.class);
		System.out.println("el id max carrito es "+total);
		return total;
	}
/*
 * @Override
	public void insert(Customer cus) {
		String sql = "INSERT INTO customer " +
				"(CUST_ID, NAME, AGE) VALUES (?, ?, ?)" ;
		getJdbcTemplate().update(sql, new Object[]{
				cus.getCustId(), cus.getName(), cus.getAge()
	
 */
	
	@Override
	public carrito insertarCarrito(carrito carrito) {
		String sql ="INSERT INTO carrito (total) values (?)";
		getJdbcTemplate().update(sql, new Object[]{
				carrito.getTotal()
				});
		return carrito;
	}

	@Override
	public usuario insertarUsuario(usuario usuario) {
		String sql ="INSERT INTO usuario (id_carrito,nombre_usuario,apellido_usuario,correo_usuario,contrasenia_usuario,rol)" + 
				"values (?,?,?,?,?,?)";
		getJdbcTemplate().update(sql, new Object[]{
				usuario.getId_carrito(),usuario.getNombre_usuario(),usuario.getApellido_usuario(),usuario.getCorreo_usuario(),usuario.getContrasenia_usuario(),usuario.getRol()});
		return usuario;
	}

	@Override
	public tarjeta ingresarTarjeta(tarjeta tarjeta) {
		String sql ="insert into tarjeta (id_usuariot,num_tarjeta,nombre_tarjeta,mes_expiracion,anio_expiracion,ccv_tarjeta) " + 
				"values (?,?,?,?,?,?)";
		getJdbcTemplate().update(sql, new Object[]{tarjeta.getId_usuariot(),tarjeta.getNum_tarjeta(),tarjeta.getNombre_tarjeta(),tarjeta.getMes_expiracion(),tarjeta.getAnio_expiracion(),tarjeta.getCcv_tarjeta()});
		
		return tarjeta;
		
		
	}

	@Override
	public productos ingresarProducto(productos producto) {
		String sql ="insert into productos (precio,nombrer,imagen,descripcion,categoria) " + 
				"values (?,?,?,?,?)";
		getJdbcTemplate().update(sql, new Object[]{producto.getPrecio(),producto.getNombre(),producto.getImagen(),producto.getDescripcion(),producto.getCategoria()});
		
		return producto;
	}

	@Override
	public void insertarImagen(ImageModel image) {
		String sql="insert into imagen (nombre,tipo,imagen) values (?,?,?)";
		getJdbcTemplate().update(sql,new Object[] {image.getName(),image.getType(),image.getPicByte()});
		System.out.println("imagen ingresada creo");
	}

	@Override
	public ImageModel cargarImagen(String id) {
		String sql="select * from imagen where nombre = '"+id+"'";
		System.out.println("Realiza la consulta con id "+id);
		ImageModel x=new ImageModel();
		x.setName(id);
		ImageModel salida=new ImageModel();
		ImageModel img=(ImageModel)getJdbcTemplate().query(sql,new ResultSetExtractor<ImageModel>() {
			@Override
			public ImageModel extractData(ResultSet rs) throws SQLException, DataAccessException {
				if(rs.next()) {
					salida.setName(rs.getString("nombre"));
					salida.setType(rs.getString("tipo"));
					salida.setPicByte(rs.getBytes("imagen"));
				}
				return salida;
			}
		});
		
		System.out.println("Se Encuentra"+img.getName());
		return img;
	}

	@Override
	public factura insertarFactura(factura factura) {
		String sql="insert into factura (id_usuario,fecha_factura) values (?,?)";
		assert getJdbcTemplate() != null;
		getJdbcTemplate().update(sql, factura.getId_usuario(),factura.getFecha_factura());
		return factura;
	}

	@Override
	public venta insertarVenta(venta venta) {
		String sql="insert into venta (id_factura,id_carrito,fecha_venta,fecha_evento,direccion_evento,direccion_entrega,precio_final) values (?,?,?,?,?,?,?)";
		assert getJdbcTemplate() !=null;
		getJdbcTemplate().update(sql,venta.getId_factura(),venta.getId_carrito(),venta.getFecha_venta(),venta.getFecha_evento(),venta.getDireccion_evento(),venta.getDireccion_Entrega(),venta.getPrecio_final());
		return venta;
	}
	@Override
	public carritoproducto insertarcarritoProducto(carritoproducto carritoproducto) {
		String sql="insert into carritoproducto (id_carrito,id_producto,fecha) values (?,?,?)";
		assert getJdbcTemplate()!=null;
		getJdbcTemplate().update(sql,carritoproducto.getId_carrito(),carritoproducto.getId_producto(),carritoproducto.getFecha());
		return carritoproducto;
	}


	
}

