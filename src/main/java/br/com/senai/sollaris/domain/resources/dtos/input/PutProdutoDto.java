package br.com.senai.sollaris.domain.resources.dtos.input;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import br.com.senai.sollaris.domain.enums.Publico;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class PutProdutoDto {
	@NotEmpty
	private String nome;
	
	private Integer categoria_id;
	
	private Integer sub_categoria_id;
	
	@NotEmpty
	private String descricao;
	
	private Double preco;
	
	@NotEmpty
	private String img;
	
	private Publico publico;
	
	@Valid
	private PutProduto_DetalheDto detalhes_do_produto;
	
}
