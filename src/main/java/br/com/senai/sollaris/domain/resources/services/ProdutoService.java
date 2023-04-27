package br.com.senai.sollaris.domain.resources.services;

import java.net.URI;

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
import br.com.senai.sollaris.domain.Produto_Detalhes;
import br.com.senai.sollaris.domain.SubCategoria;
import br.com.senai.sollaris.domain.repositories.ProdutoRepository;
import br.com.senai.sollaris.domain.repositories.Produto_DetalhesRepository;
import br.com.senai.sollaris.domain.resources.dtos.input.ProdutoDto;
import br.com.senai.sollaris.domain.resources.dtos.input.PutProdutoDto;
import br.com.senai.sollaris.domain.resources.dtos.output.ReturnProdutoDto;
import br.com.senai.sollaris.domain.resources.services.exceptions.EmpresaFeignNaoEncontrada;
import br.com.senai.sollaris.domain.resources.services.exceptions.EmpresaNaoEncontradaException;
import br.com.senai.sollaris.domain.resources.services.exceptions.ObjetoNaoEncontradoException;
import br.com.senai.sollaris.domain.resources.services.exceptions.ProdutoAlteradoException;
import br.com.senai.sollaris.domain.resources.services.exceptions.SubCategoriaNaoEncontradoException;
import br.com.senai.sollaris.domain.resources.services.validations.ValidationService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProdutoService {
	//Gerador de Log
	private final Environment env;
	
	//Consultas
	private final EmpresaFeign empresaFeign;
	private final ProdutoRepository produtoRepository;
	private final Produto_DetalhesRepository produto_DetalhesRepository;
	private final CategoriaService categoriaService;
	
	//Validações
	private final ValidationService validationService;
	
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
	
	//Falta associar os produtos retornados para a empresa
	public ResponseEntity<Page<ReturnProdutoDto>> listarProdutoPorSubCategoria(Integer id, Pageable pageable) {
		Page<Produto> pageSGDB = produtoRepository.findBySubCategoria_id(id, pageable);
		
		//Validação de Página
		if (pageSGDB.isEmpty()) 
			throw new SubCategoriaNaoEncontradoException("SubCategoria não encontrada no sistema");
		
		//Conversão de Página Entidade para Página DTO
		Page<ReturnProdutoDto> page = pageSGDB.map(produto -> new ReturnProdutoDto(produto));
		
		return ResponseEntity.ok(page);
	}
	
	@Transactional
	public ResponseEntity<ReturnProdutoDto> cadastrarProduto(ProdutoDto produtoDto, UriComponentsBuilder uriBuilder) {
		log.info("EMPRESA_SERVICE ::: Get Request on " + env.getProperty("local.server.port") + " port");
		
		try {
			//Busca a empresa e valida se existe
			ReturnEmpresaDto returnEmpresaDto = empresaFeign.retornarEmpresa(produtoDto.getEmpresa_id()).getBody();
			Empresa empresa = new Empresa(returnEmpresaDto);
			
			//Busca a categoria pelo id e valida se existe
			Categoria categoria =  categoriaService.buscarCategoria(produtoDto.getCategoria_id());
			
			//Valida subCategoria pela StreamAPI (Filter)
			SubCategoria subCategoria = validationService.validarSubCategoria(categoria, produtoDto.getSub_categoria_id());
			
			Produto produto = new Produto(produtoDto, subCategoria, empresa);
		    produtoRepository.save(produto);
		    
		    Produto_Detalhes produto_Detalhes = new Produto_Detalhes(produtoDto.getDetalhes_do_produto(), produto);
		    produto_DetalhesRepository.save(produto_Detalhes);

		    URI uri = uriBuilder.path("/api/products/{id}").buildAndExpand(produto.getId()).toUri();

		    return ResponseEntity.created(uri).body(new ReturnProdutoDto(produto, produto_Detalhes));
				
		} catch (FeignException.InternalServerError ex) {
			throw new EmpresaFeignNaoEncontrada("Empresa não foi localizada!");
		}
	}
	
	@Transactional
	public ResponseEntity<ReturnProdutoDto> alterarProduto(Long id, PutProdutoDto produtoDto) {
		//Valida o Produto se existe
		Produto produto = produtoRepository.findById(id)
				.orElseThrow(() -> new ObjetoNaoEncontradoException("Produto não localizado!"));
		
		//Valida se o detalhe do produto está vinculado com Produto e
		Produto_Detalhes produto_Detalhes = validationService.validarProdutoDetalhe(produto, produtoDto.getDetalhes_do_produto().getId());
		
		Categoria categoria =  categoriaService.buscarCategoria(produtoDto.getCategoria_id());
		
		SubCategoria subCategoria = validationService.validarSubCategoria(categoria, produtoDto.getSub_categoria_id());
		
		//vai validar se este produto contém a mesma categoria_id e sub_categoria_id
		if (validationService.validarAlteracaoProduto(produto, produtoDto)) {
			produto.atualizarInformacoes(produtoDto, subCategoria);
			produto_Detalhes.atualizarInformacoes(produtoDto.getDetalhes_do_produto());
			return ResponseEntity.ok(new ReturnProdutoDto(produto, produto_Detalhes));
		}
		
	//nova exception
		throw new ProdutoAlteradoException("Produto alterado ou id iguais");
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
