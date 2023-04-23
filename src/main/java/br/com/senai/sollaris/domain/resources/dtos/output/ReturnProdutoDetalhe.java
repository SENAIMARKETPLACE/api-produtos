package br.com.senai.sollaris.domain.resources.dtos.output;

import br.com.senai.sollaris.domain.Produto_Detalhes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class ReturnProdutoDetalhe {
	
	private Integer id;
	private String tamanho;
	private String peso;
	private String cor;
	private Long quantidade;
	
	public ReturnProdutoDetalhe(Produto_Detalhes produto_Detalhes) {
		this.id = produto_Detalhes.getId();
		this.tamanho = produto_Detalhes.getTamanho();
		this.peso = produto_Detalhes.getPeso();
		this.cor = produto_Detalhes.getCor();
		this.quantidade = produto_Detalhes.getQuantidade();
	}
}
