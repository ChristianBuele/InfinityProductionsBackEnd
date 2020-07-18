package com.javasampleapproach.jdbcpostgresql.constructor;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.javasampleapproach.jdbcpostgresql.model.Customer;
import com.javasampleapproach.jdbcpostgresql.model.carrito;
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
 @GetMapping(value = "/{id}/stock")
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
 
 
}
