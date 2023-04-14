package br.com.senai.sollaris.domain.resources.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.senai.sollaris.domain.SubCategoria;
import br.com.senai.sollaris.domain.repositories.SubCategoriaRepository;
import br.com.senai.sollaris.domain.resources.dtos.output.ReturnSubCategoriaDto;
import br.com.senai.sollaris.domain.resources.services.exceptions.CategoriaNaoEncontradoException;
import br.com.senai.sollaris.domain.resources.services.exceptions.ObjetoNaoEncontradoException;

@Service
public class SubCategoriaService {
	
	@Autowired
	private SubCategoriaRepository subCategoriaRepository;
	
	public ResponseEntity<Page<ReturnSubCategoriaDto>> listarSubCategorias(Pageable pageable) {
		return ResponseEntity.ok(subCategoriaRepository.findAll(pageable)
				.map(ReturnSubCategoriaDto::new));
				
		
	}

	public ResponseEntity<SubCategoria> listarSubCategoria(Integer id) {
		return ResponseEntity.ok(subCategoriaRepository.findById(id)
				.orElseThrow(() -> new ObjetoNaoEncontradoException("SubCategoria não encontrado no sistema")));
	}

	public void listarSubCategoriaPorCategoria(Integer id) {
		subCategoriaRepository.findByCategoria_id(id)
		.orElseThrow(() -> new CategoriaNaoEncontradoException("Categoria não encontrado no sistema"));
		
	}

}
