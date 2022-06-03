package org.generation.lojadegames.controller;

import java.util.List;

import javax.validation.Valid;

import org.generation.lojadegames.model.Produto;
import org.generation.lojadegames.repository.ProdutoRepository;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produto")
@CrossOrigin("*")
public class ProdutoController {

	@Autowired
	private ProdutoRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Produto>>getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto>buscaPorId(@PathVariable Long id){
		return repository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> buscaPorNome(@PathVariable String nome){
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@GetMapping("/precomenor/{preco}")
	public ResponseEntity<List<Produto>> buscaPorPrecoMenor(@PathVariable Double preco){
		return ResponseEntity.ok(repository.findByPrecoLessThan(preco));
	}
	
	@GetMapping("/precomenorouigual/{preco}")
	public ResponseEntity<List<Produto>> buscaPorPrecoMenorOuIgual(@PathVariable Double preco){
		return ResponseEntity.ok(repository.findByPrecoLessThanEqual(preco));
	}
	
	@GetMapping("/precomaior/{preco}")
	public ResponseEntity<List<Produto>> buscaPorPrecoMaior(@PathVariable Double preco){
		return ResponseEntity.ok(repository.findByPrecoGreaterThan(preco));
	}
	
	@GetMapping("/precomaiorouigual/{preco}")
	public ResponseEntity<List<Produto>> buscaPorPrecoMaiorOuIgual(@PathVariable Double preco){
		return ResponseEntity.ok(repository.findByPrecoGreaterThanEqual(preco));
	}
	
	@PostMapping
	public ResponseEntity<Produto> post (@Valid @RequestBody Produto produto){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produto));
	}
	
	@PutMapping
	public ResponseEntity<Produto> put (@Valid @RequestBody Produto produto){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(produto));
	}
	
	@DeleteMapping("/{id}")
	public void delete (@PathVariable Long id) {
		repository.deleteById(id);
	}
}
