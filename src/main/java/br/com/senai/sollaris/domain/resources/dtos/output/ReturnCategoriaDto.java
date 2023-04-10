package br.com.senai.sollaris.domain.resources.dtos.output;

import java.util.List;

import br.com.senai.sollaris.domain.Categoria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReturnCategoriaDto {
	
	private Integer id;
	private String nome;
	private List<ReturnSubCategoriaDto> subCategorias;
	
	public ReturnCategoriaDto(Categoria categoria) {
		this.id = categoria.getId();
		this.nome = categoria.getNome();
		this.subCategorias = categoria.getSubCategoria()
				.stream().map(ReturnSubCategoriaDto::new).toList();
		
	}
}
