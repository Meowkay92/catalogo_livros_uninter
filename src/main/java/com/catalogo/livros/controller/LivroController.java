package com.catalogo.livros.controller;

import com.catalogo.livros.model.Livro;
import com.catalogo.livros.repository.LivroRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/livros")
public class LivroController {

    private final LivroRepository repository;

    public LivroController(LivroRepository repository) {
        this.repository = repository;
    }

    // GET - Lista todos
    @GetMapping
    public List<Livro> listar() {
        return repository.findAll();
    }

    // GET - Busca por ID
    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscar(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST - Adiciona novo (201 + Location)
    @PostMapping
    public ResponseEntity<Livro> adicionar(@Valid @RequestBody Livro livro) {
        Livro salvo = repository.save(livro);
        return ResponseEntity
                .created(URI.create("/api/livros/" + salvo.getId()))
                .body(salvo);
    }

    // PUT - Atualiza existente
    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizar(@PathVariable Long id,
                                           @Valid @RequestBody Livro livro) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        livro.setId(id);
        Livro atualizado = repository.save(livro);
        return ResponseEntity.ok(atualizado);
    }

    // DELETE - Remove
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build(); // 204
    }
}
