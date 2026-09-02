package br.com.biblioteca.app.mapper;

import org.springframework.stereotype.Component;

import br.com.biblioteca.app.dto.UsuarioRequestDTO;
import br.com.biblioteca.app.dto.UsuarioResponseDTO;
import br.com.biblioteca.app.model.Usuario;

@Component
public class UsuarioMapper {

    public Usuario toEntity(UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        return usuario;
    }

    public UsuarioResponseDTO toResponseDTO(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail()
        );
    }
}
