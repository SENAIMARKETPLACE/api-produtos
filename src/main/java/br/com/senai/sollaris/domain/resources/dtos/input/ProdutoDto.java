package br.com.senai.sollaris.domain.resources.dtos.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.senai.sollaris.domain.enums.Publico;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDto {
	@NotNull
	private Long empresa_id;
	
	@NotNull
	private Integer categoria_id;
	
	@NotNull
	private Integer sub_categoria_id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String descricao;
	
	@NotNull
	private Double preco;
	
	@NotBlank
	private String img;
	
	@NotNull
	private Publico publico;
	
	@NotNull
	@Valid
	private Produto_DetalheDto detalhes_do_produto;
}
