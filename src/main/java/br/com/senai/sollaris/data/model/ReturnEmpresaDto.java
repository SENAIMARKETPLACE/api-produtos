package br.com.senai.sollaris.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReturnEmpresaDto {
	private Long id;
	private String nome_fantasia;
	private String cnpj;
	private String telefone;
	
}
