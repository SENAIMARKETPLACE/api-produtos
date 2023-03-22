package br.com.senai.sollaris.domain.resources.services;

import org.springframework.stereotype.Service;

import br.com.senai.sollaris.domain.repositories.ProdutoRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProdutoService {
	
	private final ProdutoRepository produtoRepository;
}
