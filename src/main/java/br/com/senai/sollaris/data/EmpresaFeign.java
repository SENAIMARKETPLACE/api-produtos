package br.com.senai.sollaris.data;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.senai.sollaris.data.model.ReturnEmpresaDto;

@FeignClient(name = "api-empresa", url = "http://localhost:8000")
public interface EmpresaFeign {
	
	@GetMapping("/api/business/{id}")
	public ResponseEntity<ReturnEmpresaDto> retornarEmpresa(@PathVariable Long id);
}
