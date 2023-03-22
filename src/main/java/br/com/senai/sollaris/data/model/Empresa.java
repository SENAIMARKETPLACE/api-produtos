package br.com.senai.sollaris.data.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.senai.sollaris.domain.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Empresa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	private String nome_fantasia;
	private String razao_social;
	private String cnpj;
	
	private String telefone;
	private String email;
	private String senha;
	
	@OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
	private List<Produto> produtos;
}
