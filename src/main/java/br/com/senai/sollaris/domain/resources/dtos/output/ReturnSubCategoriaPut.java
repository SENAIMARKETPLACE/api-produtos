package br.com.senai.sollaris.domain.resources.dtos.output;

import java.time.LocalDateTime;

import br.com.senai.sollaris.domain.SubCategoria;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
public class ReturnSubCategoriaPut {

	private String nome;
	private LocalDateTime dt_alteracao;
	
	public ReturnSubCategoriaPut(SubCategoria subCategoria) {
		this.nome = subCategoria.getNome();
	}
}
