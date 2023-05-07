package br.com.senai.sollaris.domain.resources.services;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.senai.sollaris.domain.Categoria;
import br.com.senai.sollaris.domain.repositories.CategoriaRepository;
import br.com.senai.sollaris.domain.resources.dtos.input.CategoriaDto;
import br.com.senai.sollaris.domain.resources.dtos.input.PutCategoriaDto;
import br.com.senai.sollaris.domain.resources.dtos.output.ReturnCategoriaDto;
import br.com.senai.sollaris.domain.resources.dtos.output.ReturnCategoriaPut;
import br.com.senai.sollaris.domain.resources.services.exceptions.CategoriaNaoEncontradoException;
import br.com.senai.sollaris.domain.resources.services.exceptions.DadosInvalidosException;
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
	
	//Utilizado pelo ProdutoService
	public Categoria buscarCategoria(Integer id) {
		return categoriaRepository.findById(id)
				.orElseThrow(() -> new DadosInvalidosException("Categoria inválida, tente novamente"));
	}
	
	public Optional<Categoria> buscarOptionalCategoria(Integer id) {
		return categoriaRepository.findById(id);
	}
	
	@Transactional
	public ResponseEntity<ReturnCategoriaDto> cadastrarCategoria(CategoriaDto categoriaDto, UriComponentsBuilder uriBuilder) {
		
		Categoria categoria = new Categoria(categoriaDto);
		
		categoriaRepository.save(categoria);
		
		URI uri = uriBuilder.path("/categoria/{id}").buildAndExpand(categoria.getId()).toUri();		
		return ResponseEntity.created(uri).body(new ReturnCategoriaDto(categoria));
		
	}

	@Transactional
	public ResponseEntity<ReturnCategoriaPut> alterarCategoria(@PathVariable Integer id, PutCategoriaDto categoriaDto) {
		Optional<Categoria> categoriaCaixa = categoriaRepository.findById(id);
		
			if(categoriaCaixa.isPresent()) {
				Categoria categoriaSGBD = categoriaCaixa.get();
				categoriaSGBD.alterar(categoriaDto);
				return ResponseEntity.ok(new ReturnCategoriaPut(categoriaSGBD));
			}
			return ResponseEntity.notFound().build();
		
	}
	
	@Transactional
	public ResponseEntity<Object> deletarCategoria(Integer id) {
		if (categoriaRepository.existsById(id)) {
			categoriaRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.notFound().build();
		
	}	

}
