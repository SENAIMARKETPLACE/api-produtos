package br.com.senai.sollaris.domain.resources.services.validations;

import org.springframework.stereotype.Service;

import br.com.senai.sollaris.domain.Categoria;
import br.com.senai.sollaris.domain.Produto;
import br.com.senai.sollaris.domain.SubCategoria;
import br.com.senai.sollaris.domain.resources.dtos.input.PutProdutoDto;
import br.com.senai.sollaris.domain.resources.services.exceptions.DadosInvalidosException;

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
}
