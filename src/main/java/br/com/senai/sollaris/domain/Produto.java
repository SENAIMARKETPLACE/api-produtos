package br.com.senai.sollaris.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.senai.sollaris.data.model.Empresa;
import br.com.senai.sollaris.domain.resources.dtos.input.ProdutoDto;
import br.com.senai.sollaris.domain.resources.dtos.input.PutProdutoDto;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "produtos")
public class Produto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Empresa empresa;
	
	private String nome;
	private String descricao;
	private Double preco;
	private String img;
	
	@ManyToOne
	private SubCategoria subCategoria;
	
	private String publico;
	private Integer quantidade;
	
	public Produto(ProdutoDto produtoDto, SubCategoria sub_categoria, Empresa empresa) {
		//Foreign keys
		this.empresa = empresa;
		this.subCategoria = sub_categoria;
		
		//Attributes
		this.nome = produtoDto.getNome();
		this.descricao = produtoDto.getDescricao();
		this.preco = produtoDto.getPreco();
		this.img = produtoDto.getImg();
		this.publico = produtoDto.getPublico();
		this.quantidade = produtoDto.getQuantidade();
	}

	public void atualizarInformacoes(PutProdutoDto produtoDto, Categoria categoria, SubCategoria subCategoria) {
		
		if (produtoDto.getNome() != null)
			this.nome = produtoDto.getNome();
		
		if (produtoDto.getCategoria_id() != null)
			this.subCategoria.setCategoria(categoria);
		
		if (produtoDto.getSub_categoria_id() != null)
			this.subCategoria = subCategoria;
		
		if (produtoDto.getDescricao() != null)
			this.descricao = produtoDto.getDescricao();
		
		if (produtoDto.getPreco() != null)
			this.preco = produtoDto.getPreco();
		
		if (produtoDto.getPublico() != null)
			this.publico = produtoDto.getPublico();
		
		if (produtoDto.getQuantidade() != null)
			this.quantidade = produtoDto.getQuantidade();
	}
}
