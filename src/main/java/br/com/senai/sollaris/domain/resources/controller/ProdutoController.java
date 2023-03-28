package br.com.senai.sollaris.domain.resources.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.senai.sollaris.domain.resources.controller.dtos.output.ReturnProdutoDto;
import br.com.senai.sollaris.domain.resources.services.ProdutoService;

@RestController
@RequestMapping("/api/products")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping
	public ResponseEntity<Page<ReturnProdutoDto>> listarProdutos(Pageable pageable) {
		return produtoService.listarProdutos(pageable);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ReturnProdutoDto> listarProduto(@PathVariable Long id) {
		return produtoService.listarProduto(id);
	}
	
	@PostMapping
	public void cadastrarProduto() {
		produtoService.cadastrarProduto();
	}
	
	@DeleteMapping("/{id}")
	public void excluirProduto(@PathVariable Long id) {
		produtoService.excluirProduto(id);
	}
	
}
