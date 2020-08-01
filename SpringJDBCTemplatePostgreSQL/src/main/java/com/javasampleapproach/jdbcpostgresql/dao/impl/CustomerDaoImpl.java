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
		String sql ="insert into productos (precio,nombre,id_imagen,descripcion,categoria) " + 
				"values (?,?,?,?,?)";
		System.out.println("se inserta la id"+producto.getImagen());
		getJdbcTemplate().update(sql, new Object[]{producto.getPrecio(),producto.getNombre(),producto.getImagen(),producto.getDescripcion(),producto.getCategoria()});
		
		return producto;
	}

	@Override
	public void insertarImagen(ImageModel image) {
		String sql="insert into imagen (tipo,imagen) values (?,?)";
		getJdbcTemplate().update(sql,new Object[] {image.getType(),image.getPicByte()});
		System.out.println("imagen ingresada creo");
	}

	@Override
	public ImageModel cargarImagen(String id) {
		String sql="select * from imagen where id_imagen = '"+id+"'";
		System.out.println("Realiza la consulta con id "+id);
		ImageModel x=new ImageModel();
		x.setName(id);
		ImageModel salida=new ImageModel();
		ImageModel img=(ImageModel)getJdbcTemplate().query(sql,new ResultSetExtractor<ImageModel>() {
			@Override
			public ImageModel extractData(ResultSet rs) throws SQLException, DataAccessException {
				if(rs.next()) {
					salida.setPicByte(rs.getBytes("imagen"));
				}
				return salida;
			}
		});
		
		System.out.println("Se Encuentra"+img.getName());
		return img;
	}

	@Override
	public List<productoDao> findAllProducts() {
		// TODO Auto-generated method stub
		/*String sql="select imagen, precio,nombre,descripcion,categoria from productos join imagen using (id_imagen)";
		List<productoDao> productos=(List<productoDao>)getJdbcTemplate().query(sql, new ResultSetExtractor<List<productoDao>>(){
			@Override
			public List<productoDao> extractData(ResultSet rs) throws SQLException, DataAccessException{
				List<productoDao> lista = new ArrayList<>();
				if(rs.next()) {
					System.out.print("el producto es "+rs.getString("nombre")+""+rs.getRow());
					productoDao pro=new productoDao();
					pro.setImagen(rs.getBytes("imagen"));
					pro.setPrecio(rs.getDouble("precio"));
					pro.setNombre(rs.getString("nombre"));
					pro.setDescripcion(rs.getString("descripcion"));
					pro.setCategoria(rs.getString("categoria"));
					lista.add(pro);
				}
				return lista;
			}
		});
		return productos;*/
		String sql = "select id_imagen, precio,nombre,descripcion,categoria from productos join imagen using (id_imagen)";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
		
		List<productoDao> result = new ArrayList<>();
		for(Map<String, Object> row:rows){
			System.out.println("productos "+result.size());
			productoDao cus=new productoDao();
			cus.setImagen((Integer)row.get("id_imagen"));
			cus.setPrecio((double)row.get("precio"));
			cus.setNombre((String)row.get("nombre"));
			cus.setDescripcion((String)row.get("descripcion"));
			cus.setCategoria((String)row.get("categoria"));
			result.add(cus);
			
		}
		
		return result;
	}
	@Override
	public int getIdUsuario(String correo) {
		
		String sql="select id_usuario from usuario where correo_usuario=?";
		try {
			int id=(Integer)getJdbcTemplate().queryForObject(sql, new Object[]{correo}, new RowMapper<Integer>(){
				@Override
				public Integer mapRow(ResultSet rs, int rwNumber) throws SQLException {
					
					int id=rs.getInt("id_usuario");
					return id;
				}
			});
			return id;
			}catch(Exception EmptyResultDataAccessException ) {
				return -1;
			}
	}


	@Override
	public boolean existeUsuario(String correo) {
		String sql = "SELECT correo_usuario from usuario where correo_usuario = ?";
		System.out.println("la consulta es "+sql );
		try {
		usuario us=(usuario)getJdbcTemplate().queryForObject(sql, new Object[]{correo}, new RowMapper<usuario>(){
			@Override
			public usuario mapRow(ResultSet rs, int rwNumber) throws SQLException {
				usuario cus=new usuario();
				cus.setCorreo_usuario(rs.getString("correo_usuario"));
				
				return cus;
			}
		});
		return true;
		}catch(Exception EmptyResultDataAccessException ) {
			return false;
		}
		
	}

	@Override
	public boolean contraseniaCorrecta(String correo,String contra) {
		String sql = "SELECT contrasenia_usuario from usuario where correo_usuario = ?";
		System.out.println("el correo "+correo+" paass "+contra );
		try {
		usuario us=(usuario)getJdbcTemplate().queryForObject(sql, new Object[]{correo}, new RowMapper<usuario>(){
			@Override
			public usuario mapRow(ResultSet rs, int rwNumber) throws SQLException {
				usuario cus=new usuario();
				cus.setContrasenia_usuario(rs.getString("contrasenia_usuario"));
				return cus;
			}
		});
		System.out.println("El usuario logueado es "+us);
		if(us.getContrasenia_usuario().equals(contra)) {
			return true;
		}else {
			return false;
		}
		
		}catch(Exception EmptyResultDataAccessException ) {
			return false;
		}
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

	@Override
	public boolean addProducto(productos producto) {
		// TODO Auto-generated method stub
		String sql ="insert into productos (precio,nombre,id_imagen,descripcion,categoria) values (?,?,?,?,?)";
		assert getJdbcTemplate() !=null;
		getJdbcTemplate().update(sql,producto.getPrecio(),producto.getNombre(),producto.getImagen(),producto.getDescripcion(),producto.getCategoria());
		return true;
	}

	@Override
	public int getIdImagen() {
		String sql = "SELECT MAX(id_imagen) FROM imagen";
		
		int total = getJdbcTemplate().queryForObject(sql, Integer.class);
		System.out.println("el id max carrito es "+total);
		return total;
	}


	
}

