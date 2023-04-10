package br.com.senai.sollaris.domain.resources.dtos.output;

import br.com.senai.sollaris.domain.SubCategoria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReturnSubCategoriaDto {
	
	private Integer id;
	private String nome;
	
	public ReturnSubCategoriaDto(SubCategoria subCategoria) {
		this.id = subCategoria.getId();
		this.nome = subCategoria.getNome();
	}
}
