package br.com.senai.sollaris.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table
public class Categoria {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	
	@OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
	private List<SubCategoria> subCategoria;
}
