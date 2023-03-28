package br.com.senai.sollaris.data.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReturnEmpresaDto {
	private Long id;
	private String nome_fantasia;
	private String cnpj;
	private String telefone;
	
	public ReturnEmpresaDto(Empresa empresa) {
		this.id = empresa.getId();
		this.nome_fantasia = empresa.getNome_fantasia();
		this.cnpj = empresa.getCnpj();
		this.telefone = empresa.getTelefone();
	}
}
