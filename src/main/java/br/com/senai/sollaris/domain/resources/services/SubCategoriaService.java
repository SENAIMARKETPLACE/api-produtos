package br.com.senai.sollaris.domain.resources.services;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.senai.sollaris.domain.SubCategoria;
import br.com.senai.sollaris.domain.repositories.SubCategoriaRepository;
import br.com.senai.sollaris.domain.resources.dtos.input.PutSubCategoriaDto;
import br.com.senai.sollaris.domain.resources.dtos.input.SubCategoriaDto;
import br.com.senai.sollaris.domain.resources.dtos.output.ReturnSubCategoriaDto;
import br.com.senai.sollaris.domain.resources.dtos.output.ReturnSubCategoriaPut;
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

	public ResponseEntity<ReturnSubCategoriaDto> cadastrarSubCategoria(SubCategoriaDto subCategoriaDto, UriComponentsBuilder uriBuilder){
		
		SubCategoria subCategoria = new SubCategoria(subCategoriaDto);
		
		subCategoriaRepository.save(subCategoria);
		
		URI uri = uriBuilder.path("/subCategoria/{id}").buildAndExpand(subCategoria.getId()).toUri();		
		return ResponseEntity.created(uri).body(new ReturnSubCategoriaDto(subCategoria));
	}
	
	public ResponseEntity<ReturnSubCategoriaPut> alterarSubCategoria(@PathVariable Integer id, PutSubCategoriaDto subCategoriaDto) {
		Optional<SubCategoria> subCategoriaCaixa = subCategoriaRepository.findById(id);
		
			if(subCategoriaCaixa.isPresent()) {
				SubCategoria subCategoriaSGBD = subCategoriaCaixa.get();
				subCategoriaSGBD.alterar(subCategoriaDto);
				return ResponseEntity.ok(new ReturnSubCategoriaPut(subCategoriaSGBD));
			}
			return ResponseEntity.notFound().build();
	}
	
	public ResponseEntity<Object> deletarSubCategoria(Integer id) {
		if (subCategoriaRepository.existsById(id)) {
			subCategoriaRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.notFound().build();
		
	}	

}
