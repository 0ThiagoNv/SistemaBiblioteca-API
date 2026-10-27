package br.com.biblioteca.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.biblioteca.app.exception.RecursoNaoEncontradoException;
import br.com.biblioteca.app.model.Autor;
import br.com.biblioteca.app.model.Categoria;
import br.com.biblioteca.app.model.Livro;
import br.com.biblioteca.app.repository.AutorRepository;
import br.com.biblioteca.app.repository.CategoriaRepository;
import br.com.biblioteca.app.repository.LivroRepository;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Livro> listarTodos() {
        return livroRepository.findAll();
    }

    public Livro buscarPorId(Long id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Livro não encontrado com id " + id));
    }

    public Autor buscarAutor(Long autorId) {
        return autorRepository.findById(autorId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Autor não encontrado com id " + autorId));
    }

    public Categoria buscarCategoria(Long categoriaId) {
        if (categoriaId == null) {
            return null;
        }
        return categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Categoria não encontrada com id " + categoriaId));
    }

    public Livro salvar(Livro livro) {
        return livroRepository.save(livro);
    }

    public Livro atualizar(Long id, Livro livro) {
        buscarPorId(id);
        livro.setId(id);
        return livroRepository.save(livro);
    }

    public void deletar(Long id) {
        buscarPorId(id);
        livroRepository.deleteById(id);
    }
}
