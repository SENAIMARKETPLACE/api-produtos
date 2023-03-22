package br.com.senai.sollaris.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senai.sollaris.domain.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

}
