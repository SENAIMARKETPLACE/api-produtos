package br.com.senai.sollaris.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senai.sollaris.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
