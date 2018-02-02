package com.maynar.fideliza.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.maynar.fideliza.services.IPuntos;

/**
 * para microservicioss @Service("restTemplateOfertasService")
 * 
 * @author rmaynar
 *
 */
@Service("restTemplatePuntosService")
public class RestTemplatePuntosService implements IPuntos {

	@Autowired
	private RestTemplate restTemplate;


	@Override
	public String consultarPuntos(String email) {
		String transactionUrl = "http://localhost:8086/gestionpuntos/usuario";

		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(transactionUrl)
				// Add query parameter
				.queryParam("email", email);

		String response = restTemplate.getForObject(builder.toUriString(), String.class);
		return response;
	}

}
