package br.com.senai.sollaris.domain.resources.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.senai.sollaris.data.EmpresaFeign;
import br.com.senai.sollaris.domain.repositories.ProdutoRepository;
import br.com.senai.sollaris.domain.resources.controller.dtos.input.ProdutoDto;
import br.com.senai.sollaris.domain.resources.controller.dtos.output.ReturnProdutoDto;
import br.com.senai.sollaris.domain.resources.services.exceptions.ObjetoNaoEncontradoException;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class ProdutoService {
	
	private final EmpresaFeign empresaFeign;
	private final ProdutoRepository produtoRepository;
	
	public ResponseEntity<Page<ReturnProdutoDto>> listarProdutos(Pageable pageable) {
		return ResponseEntity.ok(produtoRepository.findAll(pageable)
				.map(ReturnProdutoDto::new));
	}

	public ResponseEntity<ReturnProdutoDto> listarProduto(Long id) {
		return ResponseEntity.ok(produtoRepository.findById(id)
				.map(ReturnProdutoDto::new)
				.orElseThrow(() -> new ObjetoNaoEncontradoException("Produto não encontrado!")));
		
	}
	
	@Transactional
	public void cadastrarProduto(ProdutoDto produtoDto, UriComponentsBuilder uriBuilder) {
		
	}
	
	@Transactional
	public void alterarProduto() {
		
	}
	
	@Transactional
	public ResponseEntity<Object> excluirProduto(Long id) {
		if (produtoRepository.existsById(id)) {
			produtoRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.notFound().build();
	}
}
