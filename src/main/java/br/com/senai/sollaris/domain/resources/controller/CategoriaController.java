package br.com.senai.sollaris.domain.resources.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.senai.sollaris.domain.resources.dtos.input.CategoriaDto;
import br.com.senai.sollaris.domain.resources.dtos.output.ReturnCategoriaDto;
import br.com.senai.sollaris.domain.resources.services.CategoriaService;

@RestController
@RequestMapping("api/categories")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping
	public ResponseEntity<Page<ReturnCategoriaDto>> listarCategorias(Pageable pageable) {
		return categoriaService.listarCategorias(pageable);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ReturnCategoriaDto> listarCategoria(@PathVariable Integer id) {
		return categoriaService.listarCategoria(id);
	}
	
	public void cadastrarCategoria(@RequestBody @Valid CategoriaDto categoriaDto, UriComponentsBuilder uriBuilder) {
		categoriaService.cadastrarCategoria(categoriaDto, uriBuilder);
	}
	
	public void alterarCategoria(@RequestBody @Valid CategoriaDto categoriaDto) {
		categoriaService.alterarCategoria(categoriaDto);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Object> deletarCategoria(@PathVariable Integer id) {
		return categoriaService.deletarCategoria(id);
	}
}
