package br.com.senai.sollaris.domain.resources.dtos.input;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class PutCategoriaDto {

	@NotBlank
	private String nome;
	
	
}
