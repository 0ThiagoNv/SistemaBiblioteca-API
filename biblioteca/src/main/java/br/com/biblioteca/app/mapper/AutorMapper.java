package br.com.biblioteca.app.mapper;

import org.springframework.stereotype.Component;

import br.com.biblioteca.app.dto.AutorRequestDTO;
import br.com.biblioteca.app.dto.AutorResponseDTO;
import br.com.biblioteca.app.model.Autor;

@Component
public class AutorMapper {

    public Autor toEntity(AutorRequestDTO dto) {
        Autor autor = new Autor();
        autor.setNome(dto.getNome());
        autor.setNacionalidade(dto.getNacionalidade());
        return autor;
    }

    public AutorResponseDTO toResponseDTO(Autor autor) {
        return new AutorResponseDTO(
                autor.getId(),
                autor.getNome(),
                autor.getNacionalidade()
        );
    }
}
