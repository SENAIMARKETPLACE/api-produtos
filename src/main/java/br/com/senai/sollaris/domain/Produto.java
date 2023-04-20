package br.com.senai.sollaris.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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
	
	@Enumerated(EnumType.STRING)
	private Publico publico;
	private LocalDateTime dt_registro;
	private LocalDateTime dt_alteracao;
	
	@OneToMany
	private List<Produto_Detalhes> produto_Detalhes;
	
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

	public void atualizarInformacoes(PutProdutoDto produtoDto, Categoria categoria, SubCategoria subCategoria) {
		this.nome = produtoDto.getNome();
		this.subCategoria.setCategoria(categoria);
		this.subCategoria = subCategoria;
		this.descricao = produtoDto.getDescricao();
		this.preco = produtoDto.getPreco();
		this.publico = produtoDto.getPublico();
		this.dt_alteracao = LocalDateTime.now();
	}
}
