package br.com.biblioteca.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.biblioteca.app.exception.RecursoNaoEncontradoException;
import br.com.biblioteca.app.model.Usuario;
import br.com.biblioteca.app.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado com id " + id));
    }

    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizar(Long id, Usuario usuario) {
        buscarPorId(id);
        usuario.setId(id);
        return usuarioRepository.save(usuario);
    }

    public void deletar(Long id) {
        buscarPorId(id);
        usuarioRepository.deleteById(id);
    }
}
