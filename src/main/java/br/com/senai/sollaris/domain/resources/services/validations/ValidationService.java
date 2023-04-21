package br.com.senai.sollaris.domain.resources.services.validations;

import org.springframework.stereotype.Service;

import br.com.senai.sollaris.domain.Categoria;
import br.com.senai.sollaris.domain.Produto;
import br.com.senai.sollaris.domain.Produto_Detalhes;
import br.com.senai.sollaris.domain.SubCategoria;
import br.com.senai.sollaris.domain.resources.dtos.input.PutProdutoDto;
import br.com.senai.sollaris.domain.resources.services.exceptions.DadosInvalidosException;
import br.com.senai.sollaris.domain.resources.services.exceptions.Produto_DetalhesNaoVinculadoException;

@Service
public class ValidationService {
	
	//Ele faz a validação de subCategoria pela Categoria associada e o id repassado
	public SubCategoria validarSubCategoria(Categoria categoria, Integer id) {
		return categoria.getSubCategoria().stream()
		.filter(sub -> sub.getId() == id)
		.findFirst()
		.orElseThrow(() -> new DadosInvalidosException("SubCategoria inválida, tente novamente"));
	}

	public boolean validarAlteracaoProduto(Produto produto, PutProdutoDto produtoDto) {
		if (produto.getSubCategoria().getId() == produtoDto.getSub_categoria_id() &&
				produto.getSubCategoria().getCategoria().getId() == produtoDto.getCategoria_id())
			return false;
		else
			return true;
	}

	public Produto_Detalhes validarProdutoDetalhe(Produto produto, Integer id) {
		return produto.getProduto_Detalhes().stream()
				.filter(produto_detalhes -> produto.getId() == produto_detalhes.getProduto().getId() &&
				produto_detalhes.getId() == id)
				.findFirst()
				.orElseThrow(() -> 
					new Produto_DetalhesNaoVinculadoException("Detalhes do Produto não vinculado com o Produto passado"));
	}
}
