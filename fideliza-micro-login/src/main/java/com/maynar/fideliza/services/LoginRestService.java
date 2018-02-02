package com.maynar.fideliza.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.maynar.fideliza.bean.ClienteBean;
import com.maynar.fideliza.bean.UsuarioBean;
import com.maynar.fideliza.bom.Usuario;

@RestController
public class LoginRestService {

	@Autowired
	private ILoginService loginService;
	
	@RequestMapping(path = "/login", params = {"email", "password"}, method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE )
	public @ResponseBody Usuario login(@RequestParam(value = "email") String email,@RequestParam(value = "password") String password ) {
			
		Usuario user = new Usuario("", password, email);
		return loginService.login(user);
		
	}
	
	@RequestMapping(path="/registroCliente", method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody 
	public ClienteBean registroCliente(@RequestBody ClienteBean cliente) {
		
		UsuarioBean user = cliente.getDatosUsuario();
		ClienteBean client = loginService.registrarCliente(cliente);
		return client;
	}
}
