package br.com.senai.sollaris.domain.resources.dtos.output;

import br.com.senai.sollaris.domain.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReturnProdutoCategoria {
	
	private Integer id;
	private String nome;
	private ReturnSubCategoriaDto sub_categoria;
	
	public ReturnProdutoCategoria(Produto produto) {
		this.id = produto.getSubCategoria().getCategoria().getId();
		this.nome = produto.getSubCategoria().getCategoria().getNome();
		this.sub_categoria = new ReturnSubCategoriaDto(produto.getSubCategoria());
	}
}
