package br.com.senai.sollaris.domain.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senai.sollaris.domain.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{
	
	Optional<Categoria> findByNome(String nome);
}
