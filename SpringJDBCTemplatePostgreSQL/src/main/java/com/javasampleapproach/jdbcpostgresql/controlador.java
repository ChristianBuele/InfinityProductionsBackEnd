package com.javasampleapproach.jdbcpostgresql;


import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import com.javasampleapproach.jdbcpostgresql.model.*;

import com.javasampleapproach.jdbcpostgresql.util.ListarEventosPdf;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity.BodyBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
 

 @RequestMapping("files/{nombreArchivo}")
	@ResponseBody
	public void descargar(@PathVariable("nombreArchivo") String nombreArchivo,HttpServletResponse response) {
	 System.out.println("Entra a descargar");
	 try {
		Files.deleteIfExists(Paths.get("presets.zip"));
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
		ArrayList<String> lista=new ArrayList<>();
		lista.add("Portrait Saturation");
		lista.add("Vintage Cold");
		List<presets> presetsLista=servicio.getPreset(lista);
		compresor comprimir=new compresor(presetsLista);
		comprimir.comprimir();
		
		response.setContentType("application/zip");
		response.setHeader("Content-Disposition","attachment; filename=presets.zip");
		response.setHeader("Content-Transfer-Encoding", "binary");
		try {
			BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
			FileInputStream fis= new FileInputStream("presets.zip");
			int len;
			byte [] buf = new byte[1024];
			while ((len=fis.read(buf))>0) {
				bos.write(buf,0,len);
			}
			bos.close();
			response.flushBuffer();
			fis.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		comprimir.start();
	}
 /*
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
 }*/

 public int crearCarrito() {
	 System.out.println("si  eentra a agegar carrito");
	 carrito c=new carrito();
	 c.setTotal(0);
	 carrito nuevo=servicio.insertarCarrito(c);
	 int idmax=servicio.verMaxId();
 return idmax;
 }
 @PostMapping("usuario/")
 public ResponseEntity<String> agregarUsuarioPruebapost(@RequestBody usuario usuario){
		System.out.println("entra a agregar usuario en post "+usuario);
		if(!servicio.existeUsuario(usuario.getCorreo_usuario())) {
			usuario.setId_carrito(crearCarrito());
			usuario ingresado=servicio.insertarUsuario(usuario);
			if(ingresado!=null) {
				 return ResponseEntity.ok("true");
			}else {
				 return ResponseEntity.ok("A ocurrido un error al registrar, Intente de nuevo");

			}
		}else {
			return ResponseEntity.ok("El correo ya esta registrado");
		}	
 }
 @PostMapping("producto/")
 public ResponseEntity<String> agregarProducto(@RequestBody productos producto){
		
	 int id_imagen=idImagenMaximo();
	 producto.setImagen(id_imagen);
	 servicio.addProducto(producto);
	 return ResponseEntity.ok("true");
}
public int idImagenMaximo() {
	 int idmax=servicio.getIdImagen();
 return idmax;
 }
 
 @PostMapping("login/")
 public ResponseEntity<String> Login(@RequestBody usuario usuario){
		System.out.println("entra a loguear usuario en post "+usuario);
		if(servicio.existeUsuario(usuario.getCorreo_usuario())) {//si el correo del usuario esta en la base de datos
			if(servicio.contraseniaCorrecta(usuario.getCorreo_usuario(), usuario.getContrasenia_usuario())) { ///verfica que la contrase;a sea correcta
				return ResponseEntity.ok("Bienvenido");
			}else {
				return ResponseEntity.ok("Revise los datos");
			}
		}else {//si no existe el correo
			return ResponseEntity.ok("No existe usuario");
		}	
 }
 
 @PostMapping("tarjeta/")
 public ResponseEntity<HashMap<String,String>> addTarjeta(@RequestBody tarjeta tarjeta){
	 HashMap<String,String> x=new HashMap<String,String>();
	try {	
		System.out.println("entra a agregar tarjeta en post "+tarjeta.getNombre_tarjeta());
		DecimalFormat formato1 = new DecimalFormat("#.00");
		String nuevoSaldo=formato1.format(Math.random()*15000);
		tarjeta.setSaldo(Double.parseDouble(nuevoSaldo));
		tarjeta t=servicio.ingresarTarjeta(tarjeta);
		 
	 x.put("respuesta","true");
	 return ResponseEntity.ok(x);
	 }catch(Exception e) {
		 x.put("respuesta","false");
		 return ResponseEntity.ok(x);
	 }
 }/*
 @PostMapping("producto/")
 public ResponseEntity<HashMap<String,String>> addProducto(@RequestBody productos producto){
	 System.out.println("ingresando producto");
	 HashMap<String,String> x=new HashMap<String,String>();
	 productos s=servicio.ingresarProucto(producto);
	 x.put("respuesta","true");
	 return ResponseEntity.ok(x);
 }*/
 /*
 @PostMapping("/uploadFiles")
	public void upladImage(@RequestParam("files") MultipartFile[] files) throws IOException {
		System.out.println("Llegan"+files.length+" archivos" );
		ImageModel img = new ImageModel(files[0].getOriginalFilename(), files[0].getContentType(),
				compressBytes(files[0].getBytes()));
		servicio.insertarImagen(img);
		
		imageRepository.save(img);
		return ResponseEntity.status(HttpStatus.OK);
	}
*/
 @PostMapping("/uploadFiles")
	public void upladImage(@ModelAttribute FormWrapper model) throws IOException {
		System.out.println("Llega el nom "+model.getNombreProducto()+" con img "+model.getImagenProducto().getOriginalFilename());
		ImageModel img = new ImageModel(model.getImagenProducto().getOriginalFilename(), model.getImagenProducto().getContentType(),
				compressBytes(model.getImagenProducto().getBytes()));
		servicio.insertarImagen(img);
		System.out.println("se inserta imagen");
		int id_imagen=idImagenMaximo();
		System.out.println("se inserta imagen y el id es "+id_imagen);
		productos producto=new productos();
		producto.setNombre(model.getNombreProducto());
		producto.setPrecio(model.getPrecioProducto());
		producto.setCategoria(model.getCategoriaProducto());
		producto.setDescripcion(model.getDesProducto());
		producto.setImagen(id_imagen);
		System.out.println("va a ingresar producto");
		servicio.addProducto(producto);
	}
 
 @RequestMapping(value = "nombre/{nombre}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public  ResponseEntity<byte[]> getImage(@PathVariable("nombre") String imageName) throws IOException  {
		ImageModel img =servicio.cargarImagen(imageName);
		img.setPicByte(decompressBytes(img.getPicByte()));
		System.out.println("se ENCUENTRA la imagen "+img.getName()+" con bytes "+img.getPicByte().length);
		final HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		return new ResponseEntity<byte[]>(img.getPicByte(),headers,HttpStatus.CREATED);
	}
@PostMapping(value="presets/premium/")
public ResponseEntity<List<presets>> getPremiumPresets(){
	System.out.println("Entra a cargar presets preimum");
	List<presets> presetsLista=servicio.findPremiumPresets();
	
	System.out.println("termina de cargar premum");
	return ResponseEntity.ok(presetsLista);
	
}
@PostMapping(value="presets/free/")
public ResponseEntity<List<presets>> getFreePresets(){
	System.out.println("Entra a cargar presets free");
	List<presets> presetsLista=servicio.findFreePresets();
	
	System.out.println("termina de cargar free");
	return ResponseEntity.ok(presetsLista);
	
}
	
	@PostMapping(value="preset/")
	public ResponseEntity<String> addPreset(@ModelAttribute presets model) throws IOException{
		System.out.println("entra a agregar preset");
		System.out.println("Llega la foto "+model.getImagenPreset().getOriginalFilename()+" y el archivo "+model.getPreset().getOriginalFilename());
		boolean band=servicio.addPreset(model);
		if(band) {
			return ResponseEntity.ok("Preset Ingresado");
		}
		return ResponseEntity.ok("Error al ingresar Preset");
	
	}
	

	
	
@PostMapping(value="correo/{correo}")
public ResponseEntity<String> getIdUsuario(@PathVariable("correo") String correo){
	System.out.println("Entra a buscar "+correo);
	int id=servicio.getIdUsuario(correo);
	return ResponseEntity.ok(String.valueOf(id));
}

 @RequestMapping(value="cargarProductos/", method = RequestMethod.GET)
 public ResponseEntity<List<productoDao>> getAllProducts(){
	 List<productoDao> lista=new ArrayList<productoDao>();
	 System.out.println("buscando productos");
	 lista=servicio.findAllProducts();
	 for(int i=0;i<lista.size();i++) {
		 lista.get(i).setImagen(decompressBytes(lista.get(i).getImagen()));
		 lista.get(i).setImagen(lista.get(i).getImagen());
	 }
	 System.out.println("se van "+lista.size()+" fotos");
	 return ResponseEntity.ok(lista);
 }


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
	@PostMapping("factura/")
	public ResponseEntity<HashMap<String,String>> addFactura(@RequestBody factura factura){
		HashMap<String,String> x=new HashMap<String,String>();
		try {
			factura f=servicio.insertarFactura(factura);
			x.put("respuesta","true");
			return ResponseEntity.ok(x);
		}catch(Exception e) {
			x.put("respuesta","false");
			return ResponseEntity.ok(x);
		}
	}
	@PostMapping("venta/")
	public ResponseEntity<HashMap<String,String>> addVenta(@RequestBody venta venta){
		HashMap<String,String> x=new HashMap<String,String>();
		try {
			venta v=servicio.insertarVenta(venta);
			x.put("respuesta","true");
			return ResponseEntity.ok(x);
		}catch(Exception e) {
			x.put("respuesta","false");
			return ResponseEntity.ok(x);
		}
	}
	public byte[] codifcarBase64(byte[] datos) {
		Base64 codec=new Base64();
		byte [] encodec=codec.encode(datos);
		return encodec;
	}
	@PostMapping("productocarrito/")
	public ResponseEntity<HashMap<String,String>> addcarritoProducto(@RequestBody carritoproducto carritoproducto){
		HashMap<String,String> x=new HashMap<String,String>();
		try {
			carritoproducto cp=servicio.insertarcarritoProducto(carritoproducto);
			x.put("respuesta","true");
			return ResponseEntity.ok(x);
		}catch(Exception e) {
			x.put("respuesta","false");
			return ResponseEntity.ok(x);
		}
	}
	
	@PostMapping(value="getTarjeta/{id}")
	public ResponseEntity<List<tarjeta>> getTarjeta(@PathVariable("id") Integer id){
		List<tarjeta> lista=servicio.listAllTarjeta(id);
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("listarEventos/")
	public ResponseEntity<List<eventosDao>> getEventos(){
		List<eventosDao> eventos=new ArrayList<>();
		eventos=servicio.listarEventos();
		return ResponseEntity.ok(eventos);
	}
	@GetMapping("listarEventosProximos/")
	public ResponseEntity<List<eventosDao>> getEventosProximos() throws ParseException {
		List<eventosDao> eventos=new ArrayList<>();
		List<eventosDao> eventosProximos=new ArrayList<>();
		eventos=servicio.listarEventos();
		Date fecha,fechaActual;
		java.util.Date fechaSimple = new Date();
		fechaActual=ParseFecha(new SimpleDateFormat("dd/MM/yyyy").format(fechaSimple));
		for (int i=0;i<eventos.size();i++){
			fecha=ParseFecha(eventos.get(i).getFechaEvento());
			if(fechaActual.compareTo(fecha)>0) {
				eventosDao pro=new eventosDao();
				pro=eventos.get(i);
				eventosProximos.add(pro);
			}
		}
		return ResponseEntity.ok(eventosProximos);
	}
	@GetMapping("listarFacturas/{id}")
	public ResponseEntity<List<facturaDao>> getFacturasCli(@PathVariable("id")Integer id) throws ParseException {
		List<facturaDao> facturas=new ArrayList<>();
		facturas=servicio.listarFacturas(id);
		return ResponseEntity.ok(facturas);
	}
	public static Date ParseFecha(String fecha) throws java.text.ParseException {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaDate = null;
		fechaDate = formato.parse(fecha);
		return fechaDate;
	}
	@RequestMapping(value = "/pdfreport", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> citiesReport() throws IOException {
		List<eventosDao> cities = servicio.listarEventos();
		ByteArrayInputStream bis = ListarEventosPdf.eventosReport(cities);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");
		return ResponseEntity
				.ok()
				.headers(headers)
				.contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
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
 * post venta
 * {
    "id_factura": 1,
    "id_carrito":3,
    "fecha_venta":"1/7/2020",
    "fecha_evento":"3/8/2020",
    "direccion_evento":"Hacienda",
    "direccion_entrega":"Sebastian",
    "precio_final": 150
}
* agregar carrito producto
 * {
    "id_carrito": 1,
    "id_producto": 1,
    "fecha":"1/7/2020"
}
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
 * * factura post
 * {
    "id_usuario": 3,
    "correo_usuario": "10/07/2020"
}
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
 * http://localhost:8082/api/producto/factura/ agregar factura
 * http://localhost:8082/api/producto/venta/ agregar venta dar mas espacio al varchar base de datos
 * http://localhost:8082/api/producto/productocarrito/ agregar crrito producto
 */
/*
 * 
 * @PostMapping
public Producto guardarProducto(@RequestBody Producto producto) { //@reques para transformar de json a  java
	System.out.println("Producto a agregar "+producto.getNombre()+" con id "+producto.getId());
	return servicios.save(producto);
}
 */

