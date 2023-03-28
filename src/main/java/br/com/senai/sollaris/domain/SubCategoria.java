package br.com.senai.sollaris.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	
	@OneToMany(mappedBy = "subCategoria")
	private List<Produto> produto;
}
