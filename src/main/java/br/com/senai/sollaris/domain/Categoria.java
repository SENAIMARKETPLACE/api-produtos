package br.com.senai.sollaris.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.senai.sollaris.domain.resources.dtos.input.CategoriaDto;
import br.com.senai.sollaris.domain.resources.dtos.input.PutCategoriaDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categorias")
public class Categoria {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private LocalDateTime dt_registro;
	private LocalDateTime dt_alteracao;
	
	@OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
	private List<SubCategoria> subCategoria = new ArrayList<>();
	
	public Categoria(CategoriaDto categoriaDto) {
		this.nome = categoriaDto.getNome();
		this.dt_registro = LocalDateTime.now();
	}

	public void alterar(PutCategoriaDto categoriaDto) {
		this.nome = categoriaDto.getNome();
		this.dt_alteracao = LocalDateTime.now();
	}

}
