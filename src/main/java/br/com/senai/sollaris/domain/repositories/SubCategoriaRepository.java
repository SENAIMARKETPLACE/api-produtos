package br.com.senai.sollaris.domain.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senai.sollaris.domain.SubCategoria;

public interface SubCategoriaRepository extends JpaRepository<SubCategoria, Integer> {

	Optional<List<SubCategoria>> findByCategoria_id(Integer id);

}
