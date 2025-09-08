package com.catalogo.livros.repository;

import com.catalogo.livros.model.Livro;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class LivroRepository {

    private final Map<Long, Livro> livros = new HashMap<>();
    private long nextId = 1L;

    public List<Livro> findAll() {
        return new ArrayList<>(livros.values());
    }

    public Optional<Livro> findById(Long id) {
        return Optional.ofNullable(livros.get(id));
    }

    public Livro save(Livro livro) {
        if (livro.getId() == null) {
            livro.setId(nextId++);
        }
        livros.put(livro.getId(), livro);
        return livro;
    }

    public boolean existsById(Long id) {
        return livros.containsKey(id);
    }

    public void deleteById(Long id) {
        livros.remove(id);
    }
}
