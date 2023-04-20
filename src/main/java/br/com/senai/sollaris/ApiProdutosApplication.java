package br.com.senai.sollaris;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@EnableFeignClients
@OpenAPIDefinition(info = @Info(title = "API de Produto",
	version = "2.7.9", description = "CRUD para manter produto",
	license = @License(name = "Pertencente: Sollaris Marketplace", url = "https://github.com/SENAIMARKETPLACE"),
	contact = @Contact(name = "Email da Empresa", email = "sollaris@gmail.com.br"),
	termsOfService = "Terms_Service"))
public class ApiProdutosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiProdutosApplication.class, args);
	}

}
