package com.maynar.fideliza.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.maynar.fideliza.bom.Usuario;
import com.maynar.fideliza.services.ILoginService;
import com.maynar.fideliza.webapp.beans.ClienteBean;

/**
 * para microservicioss @Service("restTemplateOfertasService")
 * 
 * @author rmaynar
 *
 */
@Service("restTemplateLoginService")
public class RestTemplateLoginService implements ILoginService {

	@Autowired
	private RestTemplate restTemplate;

	public Usuario login(String email, String password) {

		String transactionUrl = "http://localhost:8084/login";

		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(transactionUrl)
				// Add query parameter
				.queryParam("email", email).queryParam("password", password);

		RestTemplate restTemplate = new RestTemplate();
		Usuario response = restTemplate.getForObject(builder.toUriString(), Usuario.class);
		return response;
	}

	public ClienteBean registrarCliente(ClienteBean cliente) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Content-Type", "application/json");

		HttpEntity <ClienteBean> httpEntity = new HttpEntity <ClienteBean> (cliente, httpHeaders);
		ClienteBean response = restTemplate.postForObject("http://localhost:8084/registroCliente", httpEntity, ClienteBean.class);
		return response;
	}

}
