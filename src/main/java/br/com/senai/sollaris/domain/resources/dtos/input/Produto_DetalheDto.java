package br.com.senai.sollaris.domain.resources.dtos.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Produto_DetalheDto {
	
	@NotBlank
	private String tamanho;
	
	@NotBlank
	private String peso;
	
	@NotBlank
	private String cor;
	
	@NotNull
	private Long quantidade;
}
