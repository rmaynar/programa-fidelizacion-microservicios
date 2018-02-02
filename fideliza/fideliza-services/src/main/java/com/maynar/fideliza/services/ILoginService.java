package com.maynar.fideliza.services;

import com.maynar.fideliza.bom.Operador;
import com.maynar.fideliza.bom.Usuario;
import com.maynar.fideliza.webapp.beans.ClienteBean;

public interface ILoginService {

	public Usuario login(Usuario usuario);
	public Usuario login(String email, String password);
	public Usuario register(Usuario usuario);
	public ClienteBean registrarCliente(ClienteBean cliente);
	public Operador registrarOperador(Operador operador);
	
}
