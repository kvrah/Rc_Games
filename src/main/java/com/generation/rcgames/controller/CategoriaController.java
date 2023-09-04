package com.generation.rcgames.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.rcgames.model.categoria;
import com.generation.rcgames.model.produto;
import com.generation.rcgames.repository.CategoriaRepository;
import com.generation.rcgames.repository.ProdutoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins="*", allowedHeaders="*")
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public ResponseEntity<List<categoria>>getAll(){
		return ResponseEntity.ok(categoriaRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<categoria>getById(@PathVariable long id){
		return categoriaRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@PostMapping
	public ResponseEntity<categoria>post(@Valid @RequestBody categoria Categoria){
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(Categoria));
	}
	
	@PutMapping
	public ResponseEntity<categoria>put(@Valid @RequestBody categoria Categoria){
		return categoriaRepository.findById(Categoria.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(Categoria)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete (@PathVariable long id) {
		
		Optional<categoria> Categoria=categoriaRepository.findById(id);
		
		if(Categoria.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		categoriaRepository.deleteById(id);
	}
	
	@GetMapping("/todos-por-categoria/{id}")
	public ResponseEntity<List<produto>> listarProdutosPorCategoria(@PathVariable long categoriaId) {
	    Optional<categoria> categoriaOptional = categoriaRepository.findById(categoriaId);

	    if (!categoriaOptional.isPresent()) {
	        return ResponseEntity.notFound().build();
	    }

	    categoria categoria = categoriaOptional.get();
	    List<produto> produtos = ProdutoRepository.findByCategoria(categoria);

	    return ResponseEntity.ok(produtos);
	}
	}
