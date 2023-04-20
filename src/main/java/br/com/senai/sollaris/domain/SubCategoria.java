package br.com.senai.sollaris.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.senai.sollaris.domain.resources.dtos.input.PutCategoriaDto;
import br.com.senai.sollaris.domain.resources.dtos.input.PutSubCategoriaDto;
import br.com.senai.sollaris.domain.resources.dtos.input.SubCategoriaDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sub_categorias")
public class SubCategoria {


	@Id @GeneratedValue
	private Integer id;
	
	@ManyToOne
	private Categoria categoria;
	private String nome;
	private LocalDateTime dt_registro;
	private LocalDateTime dt_alteracao;
	
	@OneToMany(mappedBy = "subCategoria")
	private List<Produto> produto;
	
	public SubCategoria(SubCategoriaDto subCategoriaDto) {
		this.nome = subCategoriaDto.getNome();
		this.dt_registro = LocalDateTime.now();
	}
	
	public void alterar(PutSubCategoriaDto subCategoriaDto) {
		this.nome = subCategoriaDto.getNome();
		this.dt_alteracao = LocalDateTime.now();
	}
}
