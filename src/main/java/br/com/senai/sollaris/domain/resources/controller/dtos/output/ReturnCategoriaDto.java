package br.com.senai.sollaris.domain.resources.controller.dtos.output;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReturnCategoriaDto {
	
	private Long id;
	private String nome;
	private List<ReturnSubCategoriaDto> subCategorias;
}
