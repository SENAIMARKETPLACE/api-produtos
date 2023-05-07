package br.com.senai.sollaris.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.senai.sollaris.data.model.Empresa;
import br.com.senai.sollaris.domain.enums.Publico;
import br.com.senai.sollaris.domain.resources.dtos.input.ProdutoDto;
import br.com.senai.sollaris.domain.resources.dtos.input.Produto_DetalheDto;
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
	
	@ManyToOne
	private SubCategoria subCategoria;
	
	private String nome;
	private String descricao;
	private Double preco;
	private String img;
		
	@Enumerated(EnumType.STRING)
	private Publico publico;
	private LocalDateTime dt_registro;
	private LocalDateTime dt_alteracao;
	
	@OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
	private List<Produto_Detalhes> produto_Detalhes = new ArrayList<>();
	
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
		this.dt_registro = LocalDateTime.now();
	}

	public void atualizarInformacoes(PutProdutoDto produtoDto, SubCategoria subCategoria) {
		
		if (produtoDto.getNome() != null) {
			this.nome = produtoDto.getNome();
			this.dt_alteracao = LocalDateTime.now();
		}
		
		if (produtoDto.getDescricao() != null) {
			this.descricao = produtoDto.getDescricao();
			this.dt_alteracao = LocalDateTime.now();
		}
		
		if (produtoDto.getPreco() != null) {
			this.preco = produtoDto.getPreco();
			this.dt_alteracao = LocalDateTime.now();
		}
		
		if (produtoDto.getImg() != null) {
			this.img = produtoDto.getImg();
			this.dt_alteracao = LocalDateTime.now();
		}
		
		if (produtoDto.getPublico() != null) {
			this.publico = produtoDto.getPublico();
			this.dt_alteracao = LocalDateTime.now();
		}
		
		if (produtoDto.getCategoria_id() != null && produtoDto.getSub_categoria_id() != null) {
			this.subCategoria = subCategoria;
			this.dt_alteracao = LocalDateTime.now();
		}
		
	}
	
	public void atualizarInformacoes(PutProdutoDto produtoDto) {
		if (produtoDto.getNome() != null) {
			this.nome = produtoDto.getNome();
			this.dt_alteracao = LocalDateTime.now();
		}
		
		if (produtoDto.getDescricao() != null) {
			this.descricao = produtoDto.getDescricao();
			this.dt_alteracao = LocalDateTime.now();
		}
		
		if (produtoDto.getPreco() != null) {
			this.preco = produtoDto.getPreco();
			this.dt_alteracao = LocalDateTime.now();
		}
		
		if (produtoDto.getImg() != null) {
			this.img = produtoDto.getImg();
			this.dt_alteracao = LocalDateTime.now();
		}
		
		if (produtoDto.getPublico() != null) {
			this.publico = produtoDto.getPublico();
			this.dt_alteracao = LocalDateTime.now();
		}
		
	}

	public void salvarDetalhes(Produto_DetalheDto produto_DetalheDto) {
		produto_Detalhes.add(new Produto_Detalhes(produto_DetalheDto, this));
		
	}

	
}
