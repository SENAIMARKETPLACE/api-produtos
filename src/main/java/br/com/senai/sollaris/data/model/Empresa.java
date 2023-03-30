package br.com.senai.sollaris.data.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.senai.sollaris.domain.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "empresas")
public class Empresa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome_fantasia;
	private String razao_social;
	private String cnpj;
	
	private String telefone;
	private String email;
	private String senha;
	
	@OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
	private List<Produto> produtos;
	
	public Empresa(ReturnEmpresaDto returnEmpresaDto) {
		this.id = returnEmpresaDto.getId();
		this.nome_fantasia = returnEmpresaDto.getNome_fantasia();
		this.cnpj = returnEmpresaDto.getCnpj();
		this.telefone = returnEmpresaDto.getTelefone();
	}
}
