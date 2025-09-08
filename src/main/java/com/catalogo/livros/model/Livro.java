package com.catalogo.livros.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Livro {
    private Long id;

    @NotBlank(message = "O título é obrigatório")
    @Size(min = 2, max = 120, message = "Título deve ter entre 2 e 120 caracteres")
    private String titulo;

    @NotBlank(message = "O autor é obrigatório")
    private String autor;

    @NotBlank(message = "O universo é obrigatório")
    @Pattern(regexp = "Game of Thrones|Senhor dos Anéis",
            message = "Universo deve ser 'Game of Thrones' ou 'Senhor dos Anéis'")
    private String universo;
}
