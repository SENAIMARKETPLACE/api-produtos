package br.com.senai.sollaris.domain.resources.dtos.input;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubCategoriaDto {
	
	@NotNull
	private String nome;
}
