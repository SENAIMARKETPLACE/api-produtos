package br.com.senai.sollaris.domain.resources.dtos.output;

import java.time.LocalDateTime;

import br.com.senai.sollaris.domain.Categoria;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
public class ReturnCategoriaPut {
	
	private String nome;
	private LocalDateTime dt_alteracao;

	public ReturnCategoriaPut(Categoria categoria) {
		this.nome = categoria.getNome();
		this.dt_alteracao = categoria.getDt_alteracao();
	}
}
