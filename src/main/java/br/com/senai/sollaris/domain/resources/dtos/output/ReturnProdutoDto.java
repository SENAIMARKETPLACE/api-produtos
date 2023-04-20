package br.com.senai.sollaris.domain.resources.dtos.output;

import br.com.senai.sollaris.domain.Produto;
import br.com.senai.sollaris.domain.enums.Publico;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReturnProdutoDto {
	private Long id;
	private String nome;
	private String descricao;
	private Double preco;
	private String img;
	private Publico publico;
	private ReturnProdutoCategoria categoria;
	private Integer quantidade;
	
	public ReturnProdutoDto(Produto produto) {
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.preco = produto.getPreco();
		this.img = produto.getImg();
		this.publico = produto.getPublico();
		this.categoria = new ReturnProdutoCategoria(produto);
	}
	


}
