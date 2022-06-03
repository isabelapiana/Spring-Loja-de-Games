package org.generation.lojadegames.repository;

import java.util.List;

import org.generation.lojadegames.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	public List<Produto> findAllByNomeContainingIgnoreCase (String nome);
	public List <Produto> findByPrecoLessThan (Double preco);
	public List <Produto> findByPrecoLessThanEqual (Double preco);
	public List <Produto> findByPrecoGreaterThan (Double preco);
	public List <Produto> findByPrecoGreaterThanEqual (Double preco);
}
