package com.maynar.fideliza.services;

import com.maynar.fideliza.bom.Usuario;
import com.maynar.fideliza.webapp.beans.ClienteBean;

public interface ILoginService {

	public Usuario login(String email, String password);
	public ClienteBean registrarCliente(ClienteBean cliente);
	
}
