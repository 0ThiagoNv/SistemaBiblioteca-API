package br.com.biblioteca.app.mapper;

import org.springframework.stereotype.Component;

import br.com.biblioteca.app.dto.LivroRequestDTO;
import br.com.biblioteca.app.dto.LivroResponseDTO;
import br.com.biblioteca.app.model.Autor;
import br.com.biblioteca.app.model.Livro;

@Component
public class LivroMapper {

    public Livro toEntity(LivroRequestDTO dto, Autor autor) {
        Livro livro = new Livro();
        livro.setTitulo(dto.getTitulo());
        livro.setIsbn(dto.getIsbn());
        livro.setAnoPublicacao(dto.getAnoPublicacao());
        livro.setAutor(autor);
        livro.setDisponivel(true);
        return livro;
    }

    public LivroResponseDTO toResponseDTO(Livro livro) {
        return new LivroResponseDTO(
                livro.getId(),
                livro.getTitulo(),
                livro.getIsbn(),
                livro.getAnoPublicacao(),
                livro.isDisponivel(),
                livro.getAutor() != null ? livro.getAutor().getId() : null,
                livro.getAutor() != null ? livro.getAutor().getNome() : null
        );
    }
}
