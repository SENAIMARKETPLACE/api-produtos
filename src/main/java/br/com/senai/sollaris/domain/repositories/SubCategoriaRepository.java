package br.com.senai.sollaris.domain.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senai.sollaris.domain.SubCategoria;

public interface SubCategoriaRepository extends JpaRepository<SubCategoria, Integer> {

	Page<SubCategoria> findByCategoria_id(Integer id, Pageable page);

}
