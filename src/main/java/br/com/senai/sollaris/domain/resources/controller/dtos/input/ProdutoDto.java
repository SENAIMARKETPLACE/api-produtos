package br.com.senai.sollaris.domain.resources.controller.dtos.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDto {
	@NotNull
	private Long empresa_id;
	
	@NotBlank
	private String categoria;
	
	@NotBlank
	private String sub_categoria;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String descricao;
	
	@NotNull
	private Double preco;
	
	@NotBlank
	private String img;
	
	@NotBlank
	private String publico;
	
	@NotNull
	private Integer quantidade;
}
