package br.com.senai.sollaris.cors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{
	
	// * SIGNIFICA QUE TUDO ESTÁ LIBERADO
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("http://localhost:3000")
		.allowedOrigins("*")
		.allowedHeaders("*")
		.allowedMethods("GET","POST","PUT","DELETE");
		
	}

}
