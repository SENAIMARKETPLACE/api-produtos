package br.com.senai.sollaris.domain.resources.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.senai.sollaris.domain.SubCategoria;
import br.com.senai.sollaris.domain.resources.dtos.output.ReturnSubCategoriaDto;
import br.com.senai.sollaris.domain.resources.services.SubCategoriaService;

@RestController
@RequestMapping(name = "api/sub_categories")
public class SubCategoriaController {
	
	@Autowired
	private SubCategoriaService subCategoriaService;
	
	@GetMapping
	public ResponseEntity<Page<ReturnSubCategoriaDto>> listarSubCategorias(Pageable pageable) {
		return subCategoriaService.listarSubCategorias(pageable);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<SubCategoria> listarSubCategoria(Integer id) {
		return subCategoriaService.listarSubCategoria(id);
	}
	
	@GetMapping("categories/{id}")
	public void listarSubCategoriaPorCategoria(Integer id) {
		subCategoriaService.listarSubCategoriaPorCategoria(id);
	}
}
