package com.maynar.fideliza.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.maynar.fideliza.bom.Cliente;
import com.maynar.fideliza.dao.IClienteDao;

@RestController
@RequestMapping(path = "/gestionpuntos")
public class AdministracionPuntosRestService {

	@Autowired
	private IClienteDao clienteDao;

	/**
	 * GET
	 * @return
	 */
	@RequestMapping(path = "/usuario", produces=MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	@ResponseBody public String obtenerPuntosCliente(@RequestParam("email") String email) {
		
		Cliente cliente = clienteDao.obtenerClienteEmail(email);
		if(cliente!=null) {
			return "{\"puntos\":\""+cliente.getPuntos()+"\"}";
		}else {
			return "{\"Resultado\":\"Error, no existe el cliente\"}";
		}
		
	}
	
	/**
	 * Recibe una persona en json, le cambia el nombre y devuelve en json
	 * @param persona
	 * @param nombreNuevo
	 * @return
	 */
	@RequestMapping(path = "/usuario", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
	@ResponseBody 
	public ResponseEntity<String> modificarPuntos(@RequestBody Cliente cliente) {
		HttpStatus estado = HttpStatus.OK;
		//obtenemos el cliente a partir de sus datos
		Cliente cliente_modificado = clienteDao.obtenerCliente(cliente);
		String msg_respuesta = "{\"resultado\":\"OK\"}";
		if(cliente_modificado==null) {
			estado = HttpStatus.FORBIDDEN;
			cliente_modificado = new Cliente();
			msg_respuesta = "{\"resultado\":\"ERROR: Cliente desconocido\"}";
		}else {
			//modificamos el cliente
			cliente_modificado.setPuntos(Integer.toString(Integer.parseInt(cliente_modificado.getPuntos())+Integer.parseInt(cliente.getPuntos())));
			//guardamos cambios
			clienteDao.guardarCliente(cliente_modificado);
		}
		ResponseEntity<String> respuesta = new ResponseEntity<String>(msg_respuesta, estado);
		
		return respuesta;
	}
}
