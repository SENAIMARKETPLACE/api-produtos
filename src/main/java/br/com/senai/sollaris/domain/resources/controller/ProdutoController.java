package br.com.senai.sollaris.domain.resources.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.senai.sollaris.domain.resources.services.ProdutoService;

@RestController
@RequestMapping("/api/products")
public class ProdutoController {
	
	private ProdutoService produtoService;
	
	@GetMapping
	public void listarProdutos() {
		
	}
}
