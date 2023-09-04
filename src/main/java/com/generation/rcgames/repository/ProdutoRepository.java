package com.generation.rcgames.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.rcgames.model.categoria;
import com.generation.rcgames.model.produto;

public interface ProdutoRepository extends JpaRepository <produto,Long> {

	static List<produto> findByCategoria(categoria Categoria) {
		List<produto> produtos = ProdutoRepository.findByCategoria(Categoria);
	    
	    return produtos;
	}

	static List<produto> findAllByNomeContainingIgnoreCase(String nome) {
		List<produto> produtos = ProdutoRepository.findAllByNomeContainingIgnoreCase(nome);  
	    return produtos;
	}


}