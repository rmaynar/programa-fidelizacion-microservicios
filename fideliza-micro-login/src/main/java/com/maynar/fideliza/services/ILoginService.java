package com.maynar.fideliza.services;

import com.maynar.fideliza.bean.ClienteBean;
import com.maynar.fideliza.bean.UsuarioBean;
import com.maynar.fideliza.bom.Cliente;
import com.maynar.fideliza.bom.Operador;
import com.maynar.fideliza.bom.Usuario;

public interface ILoginService {

	public Usuario login(Usuario usuario);
	public UsuarioBean register(UsuarioBean usuario);
	public ClienteBean registrarCliente(ClienteBean cliente);
	public Operador registrarOperador(Operador operador);
	
}
