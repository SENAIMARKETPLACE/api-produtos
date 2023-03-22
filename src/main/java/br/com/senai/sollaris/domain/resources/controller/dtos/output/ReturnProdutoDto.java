package br.com.senai.sollaris.domain.resources.controller.dtos.output;

import br.com.senai.sollaris.domain.Produto;
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
	private String subCategoria;
	private Integer quantidade;
	
	public ReturnProdutoDto(Produto produto) {
		
	}
}
