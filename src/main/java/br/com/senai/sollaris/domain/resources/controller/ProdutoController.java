package br.com.senai.sollaris.domain.resources.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.senai.sollaris.domain.resources.dtos.input.ProdutoDto;
import br.com.senai.sollaris.domain.resources.dtos.input.PutProdutoDto;
import br.com.senai.sollaris.domain.resources.dtos.output.ReturnProdutoDto;
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
	
	@GetMapping("my_products/{id}")
	public ResponseEntity<Page<ReturnProdutoDto>> listarProdutoPorEmpresa(@PathVariable Long id, Pageable pageable) {
		return produtoService.listarProdutoPorEmpresa(id, pageable);
	}
	
	@GetMapping("my_products_subCategories")
	public ResponseEntity<Page<ReturnProdutoDto>> listarProdutoPorSubCategoria(
			@RequestParam(name = "subCategoria", required = true) Integer subCategoria_id,
			@RequestParam(name = "empresa", required = true) Long empresa_id, Pageable pageable) {
		
		return produtoService.listarProdutoPorSubCategoria(subCategoria_id, empresa_id, pageable);
	}
	
	//Utilizado na API de Compra!
	@GetMapping("detailed_product/{id}")
	public ResponseEntity<ReturnProdutoDto> listarProduto_DetalhePorId(@PathVariable Integer id) {
		return produtoService.listarProduto_DetalhePorId(id);
	}
	
	@GetMapping("prices")
	public ResponseEntity<Page<ReturnProdutoDto>> listarProdutoPorFaixaDePreco(
			@RequestParam(required = true) Double precoInicial, 
			@RequestParam(required = true) Double precoFinal, Pageable pageable) {
		
		return produtoService.listarProdutoPorFaixaPreco(precoInicial, precoFinal, pageable);
	}
	
	@GetMapping("products_name")
	public ResponseEntity<Page<ReturnProdutoDto>> listarProdutoPorNome(@RequestParam(required = true) String nome, Pageable pageable) {
		return produtoService.listarProdutoPorNome(nome, pageable);
	}
	
	@GetMapping("categories/{id}")
	public ResponseEntity<Page<ReturnProdutoDto>> listarProdutoPorCategoria(@PathVariable Integer id, Pageable pageable) {
		return produtoService.listarProdutoPorCategoria(id, pageable);
	}
	
	@PostMapping
	public ResponseEntity<ReturnProdutoDto> cadastrarProduto(@RequestBody @Valid ProdutoDto produtoDto, UriComponentsBuilder uriBuilder) {
		 return produtoService.cadastrarProduto(produtoDto, uriBuilder);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<ReturnProdutoDto> alterarProduto(@PathVariable Long id, @RequestBody @Valid PutProdutoDto ProdutoDto) {
		return produtoService.alterarProduto(id, ProdutoDto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> excluirProduto(@PathVariable Long id) {
		return produtoService.excluirProduto(id);
	}
	
}
