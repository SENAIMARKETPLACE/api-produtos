package br.com.senai.sollaris.domain.resources.dtos.input;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class PutProduto_DetalheDto {
	@NotNull
	private Integer id;
	
	@NotEmpty
	private String tamanho;
	
	@NotEmpty
	private String peso;
	
	@NotEmpty
	private String cor;
	
	private Long quantidade;
}
