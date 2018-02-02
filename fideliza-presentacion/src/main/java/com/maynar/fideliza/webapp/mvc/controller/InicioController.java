package com.maynar.fideliza.webapp.mvc.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maynar.fideliza.services.IOfertasService;
import com.maynar.fideliza.services.IPuntos;
import com.maynar.fideliza.webapp.beans.UsuarioBean;

@Controller
public class InicioController {

	
	@Autowired
	private IOfertasService ofertasService;

	@Autowired
	private IPuntos puntosService;

	/**
	 * Consultar puntos
	 * @param model
	 * @return
	 */
	@RequestMapping(path="/consultaPuntos", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody public String consultarPuntos(HttpSession session) {
		UsuarioBean usuario = (UsuarioBean)session.getAttribute("usuario");
		return puntosService.consultarPuntos(usuario.getEmail());
	}
	
	@RequestMapping(path = "/ofertas/listado", method = RequestMethod.GET)
	public String listarOfertas(Model model, HttpSession sesion) {
		UsuarioBean usuario = (UsuarioBean) sesion.getAttribute("usuario");
		// comprobamos que se haya logado el usuario
		if (usuario != null) {
			model.addAttribute("puntos", puntosService.consultarPuntos(usuario.getEmail()));
			model.addAttribute("ofertas", ofertasService.consultarTodas());
			return "ofertas";
		} else {
			return "login";
		}
		
	}

	@RequestMapping(path = "/inicio", method = RequestMethod.GET)
	public String iniciarMenu(Map<String, Object> model, HttpServletRequest request) {
		// comprobamos que se haya logado el usuario
		UsuarioBean usuarioLogueado = (UsuarioBean) request.getSession().getAttribute("usuario");
		if (usuarioLogueado != null) {
			return "inicio";
		} else {
			return "login";
		}

	}

}
