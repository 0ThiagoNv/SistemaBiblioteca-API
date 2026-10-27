package br.com.biblioteca.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.biblioteca.app.exception.RecursoNaoEncontradoException;
import br.com.biblioteca.app.model.PerfilUsuario;
import br.com.biblioteca.app.model.Usuario;
import br.com.biblioteca.app.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado com id " + id));
    }

    public Usuario salvar(Usuario usuario) {
        if (usuario.getSenha() == null || usuario.getSenha().isBlank()) {
            usuario.setSenha("123456");
        }
        if (!usuario.getSenha().startsWith("$2a$") && !usuario.getSenha().startsWith("$2b$")) {
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        }
        if (usuario.getPerfil() == null) {
            usuario.setPerfil(PerfilUsuario.LEITOR);
        }
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizar(Long id, Usuario usuario) {
        Usuario existente = buscarPorId(id);
        usuario.setId(id);
        if (usuario.getSenha() == null || usuario.getSenha().isBlank()) {
            usuario.setSenha(existente.getSenha());
        } else if (!usuario.getSenha().startsWith("$2a$") && !usuario.getSenha().startsWith("$2b$")) {
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        }
        if (usuario.getPerfil() == null) {
            usuario.setPerfil(existente.getPerfil());
        }
        return usuarioRepository.save(usuario);
    }

    public void deletar(Long id) {
        buscarPorId(id);
        usuarioRepository.deleteById(id);
    }
}
