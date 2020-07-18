package com.javasampleapproach.jdbcpostgresql.constructor;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity.BodyBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javasampleapproach.jdbcpostgresql.model.Customer;
import com.javasampleapproach.jdbcpostgresql.model.ImageModel;
import com.javasampleapproach.jdbcpostgresql.model.carrito;
import com.javasampleapproach.jdbcpostgresql.model.productos;
import com.javasampleapproach.jdbcpostgresql.model.tarjeta;
import com.javasampleapproach.jdbcpostgresql.model.usuario;
import com.javasampleapproach.jdbcpostgresql.service.CustomerService;

@RestController
@RequestMapping("/api/producto")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
public class controlador {
 @Autowired
 CustomerService servicio;
 
 @GetMapping(value="listar/")
 public ResponseEntity<List<Customer>> getEmployees() {
	 System.out.println("si  eentra");
 return ResponseEntity.ok(servicio.loadAllCustomer());
 }
 
 @GetMapping(value="crearcarrito/")
 public ResponseEntity<HashMap<String,Integer>> crearCarrito() {
	 System.out.println("si  eentra a agegar carrito");
	 carrito c=new carrito();
	 c.setTotal(0);
	 carrito nuevo=servicio.insertarCarrito(c);
	 int idmax=servicio.verMaxId();
	 HashMap <String, Integer> map = new HashMap <String, Integer> ();
	 map.put("idmax", idmax);
 return ResponseEntity.ok(map);
 }

 @PostMapping("usuario/")
 public ResponseEntity<usuario> agregarUsuarioPruebapost(@RequestBody usuario usuario){
		System.out.println("entra a agregar usuario en post "+usuario.getCorreo_usuario());
		usuario ingresado=servicio.insertarUsuario(usuario);
		if(ingresado!=null) {
			 return ResponseEntity.ok(ingresado);
		}
		return ResponseEntity.notFound().build();
 }
 @PostMapping("tarjeta/")
 public ResponseEntity<HashMap<String,String>> addTarjeta(@RequestBody tarjeta tarjeta){
	 HashMap<String,String> x=new HashMap<String,String>();
	try {	System.out.println("entra a agregar tarjeta en post "+tarjeta.getNombre_tarjeta());
		tarjeta t=servicio.ingresarTarjeta(tarjeta);
		 
	 x.put("respuesta","true");
	 return ResponseEntity.ok(x);
	 }catch(Exception e) {
		 x.put("respuesta","false");
		 return ResponseEntity.ok(x);
	 }
 }
 @PostMapping("producto/")
 public ResponseEntity<HashMap<String,String>> addProducto(@RequestBody productos producto){
	 System.out.println("ingresando producto");
	 HashMap<String,String> x=new HashMap<String,String>();
	 productos s=servicio.ingresarProducto(producto);
	 x.put("respuesta","true");
	 return ResponseEntity.ok(x);
 }
 
 @PostMapping("/uploadFiles")
	public void upladImage(@RequestParam("files") MultipartFile[] files) throws IOException {
		System.out.println("Original Image Byte Size - " + files[0].getName());
		ImageModel img = new ImageModel(files[0].getOriginalFilename(), files[0].getContentType(),
				compressBytes(files[0].getBytes()));
		servicio.insertarImagen(img);
		System.out.println("si vale");
		/*imageRepository.save(img);
		return ResponseEntity.status(HttpStatus.OK);*/
	}
 
/*	@GetMapping(value = "nombre/{nombre}",produces = MediaType.IMAGE_PNG_VALUE)
	public @ResponseBody byte[] getImage(@PathVariable("nombre") String imageName)  {
		System.out.println("se busca la imagen "+imageName);
		ImageModel img =servicio.cargarImagen(imageName);
		if(img!=null) {
			return img.getPicByte();	
		}
		return	null;
		
	}*/
 
 private static final Map<String, MediaType> TYPE_MAP = new HashMap();
 

	public static byte[] compressBytes(byte[] data){
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();
		} catch (IOException e) {
		}
		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
		return outputStream.toByteArray();
	}
	// uncompress the image bytes before returning it to the angular application
	public static byte[] decompressBytes(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			outputStream.close();
		} catch (IOException ioe) {
		} catch (DataFormatException e) {
		}
		return outputStream.toByteArray();
	}
}
 
 

 
 

/*
 * {
    "id_usuario": 0,
    "id_carrito": 2,
    "nombre_usuario": "matias",
    "apellido_usuario": "buele",
    "correo_usuario": "matias@gmail.com",
    "contrasenia_usuario": "1234",
    "rol": "usuario"
}
 * 
 * 
 * 
 * 
 * 
 * 
 * */

/*posts
 * {
    "id_usuario": 0,
    "id_carrito": 3,
    "nombre_usuario": "pruebapost",
    "apellido_usuario": "jaja",
    "correo_usuario": "matias@asasas.com",
    "contrasenia_usuario": "123asas",
    "rol": "usuario"
}
 * 
 */

/*si vale esto
 * 
 *  @GetMapping(value = "/{id}/stock")
public ResponseEntity<usuario> agregarUsuario(@PathVariable  int id ,@RequestParam(name = "quantity", required = true) String quantity){
		System.out.println("entra a agregar usuario");
		usuario nuevo=new usuario();
		nuevo.setId_carrito(0);
		nuevo.setId_carrito(id);
		String [] x=quantity.split(",");
		nuevo.setNombre_usuario(x[0]);//agrego el nombre
		nuevo.setApellido_usuario(x[1]);//addapelliod;
		nuevo.setCorreo_usuario(x[2]);
		nuevo.setContrasenia_usuario(x[3]);
		nuevo.setRol("usuario");
		usuario ingresado=servicio.insertarUsuario(nuevo);
		if(ingresado!=null) {
			 return ResponseEntity.ok(ingresado);
		}
		return ResponseEntity.notFound().build();
   
   
}
 * 
 * http://localhost:8082/api/producto/tarjeta/ para agregar tarjeta
 * 
 * 
 */
/*
 * 
 * @PostMapping
public Producto guardarProducto(@RequestBody Producto producto) { //@reques para transformar de json a  java
	System.out.println("Producto a agregar "+producto.getNombre()+" con id "+producto.getId());
	return servicios.save(producto);
}
 */

