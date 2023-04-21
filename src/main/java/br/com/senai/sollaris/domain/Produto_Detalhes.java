package br.com.senai.sollaris.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.senai.sollaris.domain.resources.dtos.input.Produto_DetalheDto;
import br.com.senai.sollaris.domain.resources.dtos.input.PutProduto_DetalheDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "produtos_detalhes")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Produto_Detalhes implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String tamanho;
	private String peso;
	private String cor;
	private Long quantidade;
	
	@ManyToOne
	private Produto produto;
	
	//Usado para cadastrar Produto_Detalhes
	public Produto_Detalhes(Produto_DetalheDto detalhes_do_produto, Produto produto) {
		this.tamanho = detalhes_do_produto.getTamanho();
		this.peso = detalhes_do_produto.getPeso();
		this.cor = detalhes_do_produto.getCor();
		this.quantidade = detalhes_do_produto.getQuantidade();
		
		this.produto = produto;
	}
	
	//Usado para atualizar Produto_Detalhes
	public void atualizarInformacoes(PutProduto_DetalheDto detalhes_do_produto) {
		this.tamanho = detalhes_do_produto.getTamanho();
		this.peso = detalhes_do_produto.getPeso();
		this.cor = detalhes_do_produto.getCor();
		this.quantidade = detalhes_do_produto.getQuantidade();
		
	}
	
}
