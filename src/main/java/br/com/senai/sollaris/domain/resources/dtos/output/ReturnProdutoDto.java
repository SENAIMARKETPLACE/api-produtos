package br.com.senai.sollaris.domain.resources.dtos.output;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.senai.sollaris.domain.Produto;
import br.com.senai.sollaris.domain.Produto_Detalhes;
import br.com.senai.sollaris.domain.enums.Publico;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class ReturnProdutoDto {
	private Long id;
	private String nome;
	private String descricao;
	private Double preco;
	private String img;
	private Publico publico;
	private ReturnProdutoCategoria categoria; 
	private List<ReturnProdutoDetalhe> detalhes_dos_produtos;
	private ReturnProdutoDetalhe detalhes_do_produto;
	
	//Utilizado para retornar produtos
	public ReturnProdutoDto(Produto produto) {
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.preco = produto.getPreco();
		this.img = produto.getImg();
		this.publico = produto.getPublico();
		this.categoria = new ReturnProdutoCategoria(produto);
		this.detalhes_dos_produtos = produto.getProduto_Detalhes().stream().map(ReturnProdutoDetalhe::new).toList();
	
	}
	
	//Utilizado para retornar um produto cadastrado
	public ReturnProdutoDto(Produto produto, Produto_Detalhes produto_Detalhes) {
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.preco = produto.getPreco();
		this.img = produto.getImg();
		this.publico = produto.getPublico();
		this.categoria = new ReturnProdutoCategoria(produto);
		this.detalhes_do_produto = new ReturnProdutoDetalhe(produto_Detalhes);
	}


}
