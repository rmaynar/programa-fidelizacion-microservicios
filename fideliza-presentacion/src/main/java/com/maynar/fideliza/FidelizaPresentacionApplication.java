package com.maynar.fideliza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@SpringBootApplication
public class FidelizaPresentacionApplication {

	public static void main(String[] args) {
		SpringApplication.run(FidelizaPresentacionApplication.class, args);
	}
//	@Bean
//	public ViewResolver internalResourceViewResolver() {
//		return new InternalResourceViewResolver("/WEB-INF/jsp/", ".jsp");
//	}
//	
//	public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addViewController("/").setViewName("login");
//	}
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
}
