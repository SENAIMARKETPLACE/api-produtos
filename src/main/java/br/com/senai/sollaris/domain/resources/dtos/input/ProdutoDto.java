package br.com.senai.sollaris.domain.resources.dtos.input;

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
	
	@NotBlank
	private Integer categoria_id;
	
	@NotBlank
	private Integer sub_categoria_id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String descricao;
	
	@NotNull
	private Double preco;
	
	@NotBlank
	private String img;
	
	@NotBlank
	private Publico publico;
	
	@NotNull
	private Integer quantidade;
}
