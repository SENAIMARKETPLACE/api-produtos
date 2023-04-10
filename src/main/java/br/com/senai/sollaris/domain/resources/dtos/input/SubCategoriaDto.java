package br.com.senai.sollaris.domain.resources.dtos.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubCategoriaDto {
	
	private Long categoria_id;
	private String nome;
}
