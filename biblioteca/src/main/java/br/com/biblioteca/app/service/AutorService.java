package br.com.biblioteca.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.biblioteca.app.exception.RecursoNaoEncontradoException;
import br.com.biblioteca.app.model.Autor;
import br.com.biblioteca.app.repository.AutorRepository;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public List<Autor> listarTodos() {
        return autorRepository.findAll();
    }

    public Autor buscarPorId(Long id) {
        return autorRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Autor não encontrado com id " + id));
    }

    public Autor salvar(Autor autor) {
        return autorRepository.save(autor);
    }

    public Autor atualizar(Long id, Autor autor) {
        buscarPorId(id);
        autor.setId(id);
        return autorRepository.save(autor);
    }

    public void deletar(Long id) {
        buscarPorId(id);
        autorRepository.deleteById(id);
    }
}
