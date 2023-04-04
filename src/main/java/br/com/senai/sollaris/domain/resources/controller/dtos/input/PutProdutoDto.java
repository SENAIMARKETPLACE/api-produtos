package br.com.senai.sollaris.domain.resources.controller.dtos.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class PutProdutoDto {
	
	private String nome;
	private String descricao;
	private Double preco;
	private String img;
	private String publico;
	private Integer quantidade;
}
