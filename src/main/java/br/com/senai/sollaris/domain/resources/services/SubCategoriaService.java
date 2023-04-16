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

	public ResponseEntity<ReturnSubCategoriaDto> listarSubCategoria(Integer id) {
		return ResponseEntity.ok(subCategoriaRepository.findById(id)
				.map(ReturnSubCategoriaDto::new)
				.orElseThrow(() -> new ObjetoNaoEncontradoException("SubCategoria não encontrado no sistema")));
	}

	public ResponseEntity<Page<ReturnSubCategoriaDto>> listarSubCategoriaPorCategoria(Integer id, Pageable pageable) {
		Page<SubCategoria> pageSGDB = subCategoriaRepository.findByCategoria_id(id, pageable);
		
		//Validação de Página
		if (pageSGDB.isEmpty()) 
			throw new CategoriaNaoEncontradoException("Categoria não encontrado no sistema");
		
		//Conversão de Página Entidade para Página DTO
		Page<ReturnSubCategoriaDto> page = pageSGDB.map(sub_categoria -> new ReturnSubCategoriaDto(sub_categoria));
		
		return ResponseEntity.ok(page);
	}

}
