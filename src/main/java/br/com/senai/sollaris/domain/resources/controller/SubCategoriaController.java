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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.senai.sollaris.domain.resources.dtos.input.PutSubCategoriaDto;
import br.com.senai.sollaris.domain.resources.dtos.input.SubCategoriaDto;
import br.com.senai.sollaris.domain.resources.dtos.output.ReturnSubCategoriaDto;
import br.com.senai.sollaris.domain.resources.dtos.output.ReturnSubCategoriaPut;
import br.com.senai.sollaris.domain.resources.services.SubCategoriaService;

@RestController
@RequestMapping(path = "api/sub_categories")
public class SubCategoriaController {
	
	@Autowired
	private SubCategoriaService subCategoriaService;
	
	@GetMapping
	public ResponseEntity<Page<ReturnSubCategoriaDto>> listarSubCategorias(Pageable pageable) {
		return subCategoriaService.listarSubCategorias(pageable);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ReturnSubCategoriaDto> listarSubCategoria(@PathVariable Integer id) {
		return subCategoriaService.listarSubCategoria(id);
	}
	
	@GetMapping("categories/{id}")
	public ResponseEntity<Page<ReturnSubCategoriaDto>> listarSubCategoriaPorCategoria(@PathVariable Integer id, Pageable pageable) {
		return subCategoriaService.listarSubCategoriaPorCategoria(id, pageable);
	}
	
	@PostMapping
	public ResponseEntity<ReturnSubCategoriaDto> cadastrarSubCategoria(@RequestBody @Valid SubCategoriaDto subCategoriaDto, UriComponentsBuilder uriBuilder) {
		return subCategoriaService.cadastrarSubCategoria(subCategoriaDto, uriBuilder);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<ReturnSubCategoriaPut> alterarSubCategoria(@PathVariable Integer id, @RequestBody @Valid PutSubCategoriaDto subCategoriaDto) {
		return subCategoriaService.alterarSubCategoria(id, subCategoriaDto);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Object> deletarSubCategoria(@PathVariable Integer id) {
		return subCategoriaService.deletarSubCategoria(id);
	}
}
