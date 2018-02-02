package com.maynar.fideliza.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maynar.fideliza.bean.ClienteBean;
import com.maynar.fideliza.bean.UsuarioBean;
import com.maynar.fideliza.bom.Cliente;
import com.maynar.fideliza.bom.Operador;
import com.maynar.fideliza.bom.Usuario;
import com.maynar.fideliza.dao.IClienteDao;
import com.maynar.fideliza.dao.IUsuarioDao;

@Service
public class LoginService implements ILoginService {
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Autowired
	private IClienteDao clienteDao;

	public Usuario login(Usuario usuario) {
		Usuario user = usuarioDao.obtenerUsuario(usuario);
		return user;
	}

	@Override
	public UsuarioBean register(UsuarioBean usuario) {
		if(usuario!=null) {
			usuario.setTipo("cliente");
			Usuario user = usuarioDao.crearUsuario(new Usuario(usuario.getUsuario(), usuario.getPassword(), usuario.getPassword(), usuario.getTipo()));
			if(user!=null) {
				return usuario;
			}
		}
			return null;
		
	}

	@Override
	public ClienteBean registrarCliente(ClienteBean cliente) {
		UsuarioBean user = register(cliente.getDatosUsuario());
		if(user!=null) {//se ha guardado usuario
			Cliente cli = new Cliente();
			cli.setId_usuario(cliente.getDatosUsuario().getEmail());
			cli.setPuntos("0");
			cli = clienteDao.guardarCliente(cli);
			if(cli!=null) {//se ha guardado cliente
				return cliente;
			}else {//hacemos rollback
				usuarioDao.eliminarUsuario(new Usuario(user.getId()));
			}
		}
		return null;
	}

	@Override
	public Operador registrarOperador(Operador operador) {
		// TODO Auto-generated method stub
		return null;
	}

}
