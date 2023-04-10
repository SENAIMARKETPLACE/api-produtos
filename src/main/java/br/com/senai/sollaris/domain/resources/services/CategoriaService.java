package br.com.senai.sollaris.domain.resources.services;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.senai.sollaris.domain.repositories.CategoriaRepository;
import br.com.senai.sollaris.domain.resources.dtos.input.CategoriaDto;
import br.com.senai.sollaris.domain.resources.dtos.output.ReturnCategoriaDto;
import br.com.senai.sollaris.domain.resources.services.exceptions.CategoriaNaoEncontradoException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CategoriaService {
	
	private final CategoriaRepository categoriaRepository;
	
	public ResponseEntity<Page<ReturnCategoriaDto>> listarCategorias(Pageable pageable) {
		return ResponseEntity.ok(categoriaRepository.findAll(pageable)
				.map(ReturnCategoriaDto::new));
		
	}

	public ResponseEntity<ReturnCategoriaDto> listarCategoria(Integer id) {
		return ResponseEntity.ok(categoriaRepository.findById(id)
				.map(ReturnCategoriaDto::new)
				.orElseThrow(() -> new CategoriaNaoEncontradoException("Categoria não encontrada no sistema!")));
		
	}
	
	public void cadastrarCategoria(@Valid CategoriaDto categoriaDto, UriComponentsBuilder uriBuilder) {
		// TODO Auto-generated method stub
		
	}


	public void alterarCategoria(@Valid CategoriaDto categoriaDto) {
		// TODO Auto-generated method stub
		
	}
	
	public ResponseEntity<Object> deletarCategoria(Integer id) {
		if (categoriaRepository.existsById(id)) {
			categoriaRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.notFound().build();
		
	}	

}
