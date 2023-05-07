package br.com.senai.sollaris.domain.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.senai.sollaris.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	@Query("SELECT p FROM Produto p WHERE p.empresa.id = :id")
	Page<Produto> buscarPorEmpresa_id(@Param("id") Long id,Pageable pageable);
	
	Page<Produto> findByEmpresa_id(Long id, Pageable pageable);
	
	@Query("SELECT p FROM Produto p WHERE p.empresa.id = :empresa_id AND p.subCategoria.id = :subCategoria_id")
	Page<Produto> buscarProdutosPorSubCategoria(@Param("empresa_id") Long empresa_id, 
			@Param("subCategoria_id") Integer subCategoria_id,Pageable pageable);
	
	@Query("SELECT p FROM Produto p WHERE p.preco >= :preco_inicial AND p.preco <= :preco_final")
	Page<Produto> buscarProdutoPorFaixaPreco(@Param("preco_inicial") Double precoInicial, 
			@Param("preco_final") Double precoFinal, Pageable pageable);
	
	@Query("SELECT p FROM Produto p WHERE p.nome LIKE %:nome%")
	Page<Produto> buscarProdutosPorNome(@Param("nome") String nome, Pageable pageable);

}
