package br.com.senai.sollaris.domain.resources.services;

import java.net.URI;
import java.util.List;

import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.senai.sollaris.data.EmpresaFeign;
import br.com.senai.sollaris.data.model.Empresa;
import br.com.senai.sollaris.data.model.ReturnEmpresaDto;
import br.com.senai.sollaris.domain.Categoria;
import br.com.senai.sollaris.domain.Produto;
import br.com.senai.sollaris.domain.SubCategoria;
import br.com.senai.sollaris.domain.repositories.CategoriaRepository;
import br.com.senai.sollaris.domain.repositories.ProdutoRepository;
import br.com.senai.sollaris.domain.repositories.SubCategoriaRepository;
import br.com.senai.sollaris.domain.resources.dtos.input.ProdutoDto;
import br.com.senai.sollaris.domain.resources.dtos.input.PutProdutoDto;
import br.com.senai.sollaris.domain.resources.dtos.output.ReturnProdutoDto;
import br.com.senai.sollaris.domain.resources.services.exceptions.CategoriaNaoEncontradoException;
import br.com.senai.sollaris.domain.resources.services.exceptions.EmpresaFeignNaoEncontrada;
import br.com.senai.sollaris.domain.resources.services.exceptions.EmpresaNaoEncontradaException;
import br.com.senai.sollaris.domain.resources.services.exceptions.ObjetoNaoEncontradoException;
import br.com.senai.sollaris.domain.resources.services.exceptions.SubCategoriaNaoEncontradoException;
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
	private final CategoriaRepository categoriaRepository;
	private final SubCategoriaRepository subCategoriaRepository;
	
	public ResponseEntity<Page<ReturnProdutoDto>> listarProdutos(Pageable pageable) {
		return ResponseEntity.ok(produtoRepository.findAll(pageable)
				.map(ReturnProdutoDto::new));
	}

	public ResponseEntity<ReturnProdutoDto> listarProduto(Long id) {
		return ResponseEntity.ok(produtoRepository.findById(id)
				.map(ReturnProdutoDto::new)
				.orElseThrow(() -> new ObjetoNaoEncontradoException("Produto não encontrado!")));
		
	}
	
	public ResponseEntity<Page<ReturnProdutoDto>> listarProdutoPorEmpresa(Long id, Pageable pageable) {
		Page<ReturnProdutoDto> page = produtoRepository.findByEmpresa_id(id, pageable).map(ReturnProdutoDto::new);
		
		if (page.isEmpty())
			throw new EmpresaNaoEncontradaException("Não existe nenhum produto vinculado para essa empresa");
		
		return ResponseEntity.ok(page);
		
	}
	
	@Transactional
	public ResponseEntity<ReturnProdutoDto> cadastrarProduto(ProdutoDto produtoDto, UriComponentsBuilder uriBuilder) {
		log.info("EMPRESA_SERVICE ::: Get Request on " + env.getProperty("local.server.port") + " port");
		Produto produto;
		
		try {
			//Busca a empresa e valida se existe
			ReturnEmpresaDto returnEmpresaDto = empresaFeign.retornarEmpresa(produtoDto.getEmpresa_id()).getBody();
			Empresa empresa = new Empresa(returnEmpresaDto);
			
			//Busca a categoria pelo nome e valida se existe
			Categoria categoria =  categoriaRepository.findByNome(produtoDto.getCategoria())
				.orElseThrow(() -> new CategoriaNaoEncontradoException("Esta categoria não existe"));
			
			//Busca a sub_categoria pela categoria_id e devolve uma lista
			List<SubCategoria> listaSubCategoria = subCategoriaRepository
						.findByCategoria_id(categoria.getId())
						.orElseThrow(() -> new SubCategoriaNaoEncontradoException("Não há nenhuma sub_categoria!"));
			
			for (SubCategoria sub_categoria : listaSubCategoria) {
				
				if (sub_categoria.getNome().equals(produtoDto.getSub_categoria())) {
					produto = new Produto(produtoDto, sub_categoria, empresa);
					produtoRepository.save(produto);
					
					URI uri = uriBuilder.path("/api/products/{id}").buildAndExpand(produto.getId()).toUri();
					return ResponseEntity.created(uri).body(new ReturnProdutoDto(produto));
				}
				
			}
			
			
		} catch (IllegalArgumentException e) {
			
		} catch (FeignException.InternalServerError ex) {
			throw new EmpresaFeignNaoEncontrada("Empresa não foi localizada!");
		}
		
		return ResponseEntity.badRequest().build();
		
	}
	
	@Transactional
	public ResponseEntity<ReturnProdutoDto> alterarProduto(Long id, PutProdutoDto produtoDto) {
		Produto produto = produtoRepository.findById(id)
				.orElseThrow(() -> new ObjetoNaoEncontradoException("Produto não localizado!"));
		
		produto.atualizarInformacoes(produtoDto);
		
		return ResponseEntity.ok(new ReturnProdutoDto(produto));
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
