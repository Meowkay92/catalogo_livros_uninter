package com.catalogo.livros.config;

import com.catalogo.livros.model.Livro;
import com.catalogo.livros.repository.LivroRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final LivroRepository repo;

    public DataLoader(LivroRepository repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) {
        repo.save(new Livro(null, "A Guerra dos Tronos",
                "George R. R. Martin", "Game of Thrones"));
        repo.save(new Livro(null, "A Sociedade do Anel",
                "J. R. R. Tolkien", "Senhor dos Anéis"));
        repo.save(new Livro(null, "A Fúria dos Reis",
                "George R. R. Martin", "Game of Thrones"));
    }
}
