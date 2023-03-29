package br.com.senai.sollaris.domain.resources.services;

import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.senai.sollaris.data.EmpresaFeign;
import br.com.senai.sollaris.data.model.ReturnEmpresaDto;
import br.com.senai.sollaris.domain.repositories.ProdutoRepository;
import br.com.senai.sollaris.domain.resources.controller.dtos.input.ProdutoDto;
import br.com.senai.sollaris.domain.resources.controller.dtos.input.PutProdutoDto;
import br.com.senai.sollaris.domain.resources.controller.dtos.output.ReturnProdutoDto;
import br.com.senai.sollaris.domain.resources.services.exceptions.EmpresaFeignNaoEncontrada;
import br.com.senai.sollaris.domain.resources.services.exceptions.ObjetoNaoEncontradoException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProdutoService {
	
	
	private final Environment env;
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
	public ReturnEmpresaDto cadastrarProduto(ProdutoDto produtoDto, UriComponentsBuilder uriBuilder) {
		log.info("EMPRESA_SERVICE ::: Get Request on " + env.getProperty("local.server.port") + " port");
		
		try {
			ReturnEmpresaDto returnEmpresaDto = empresaFeign.retornarEmpresa(produtoDto.getEmpresa_id()).getBody();
			
			
			return returnEmpresaDto;
		} catch (IllegalArgumentException e) {
			
		} catch (FeignException.InternalServerError ex) {
			throw new EmpresaFeignNaoEncontrada("Empresa não foi localizada!");
		}
		
		return null;
	}
	
	@Transactional
	public void alterarProduto(Long id, PutProdutoDto produtoDto) {
		
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
