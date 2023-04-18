package br.com.senai.sollaris.domain.resources.dtos.input;

import br.com.senai.sollaris.domain.enums.Publico;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class PutProdutoDto {
	
	private String nome;
	private Integer categoria_id;
	private Integer sub_categoria_id;
	private String descricao;
	private Double preco;
	private String img;
	private Publico publico;
	private Integer quantidade;
}
