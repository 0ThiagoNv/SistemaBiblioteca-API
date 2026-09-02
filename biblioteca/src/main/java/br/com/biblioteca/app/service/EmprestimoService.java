package br.com.biblioteca.app.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.biblioteca.app.exception.LivroIndisponivelException;
import br.com.biblioteca.app.exception.RecursoNaoEncontradoException;
import br.com.biblioteca.app.model.Emprestimo;
import br.com.biblioteca.app.model.Livro;
import br.com.biblioteca.app.model.StatusEmprestimo;
import br.com.biblioteca.app.model.Usuario;
import br.com.biblioteca.app.repository.EmprestimoRepository;
import br.com.biblioteca.app.repository.LivroRepository;
import br.com.biblioteca.app.repository.UsuarioRepository;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Emprestimo> listarTodos() {
        return emprestimoRepository.findAll();
    }

    public Emprestimo buscarPorId(Long id) {
        return emprestimoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Empréstimo não encontrado com id " + id));
    }

    public Emprestimo criar(Long livroId, Long usuarioId, LocalDate dataDevolucaoPrevista) {
        Livro livro = livroRepository.findById(livroId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Livro não encontrado com id " + livroId));

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado com id " + usuarioId));

        if (!livro.isDisponivel()) {
            throw new LivroIndisponivelException("O livro '" + livro.getTitulo() + "' já está emprestado");
        }

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setLivro(livro);
        emprestimo.setUsuario(usuario);
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setDataDevolucaoPrevista(dataDevolucaoPrevista);
        emprestimo.setStatus(StatusEmprestimo.ATIVO);

        livro.setDisponivel(false);
        livroRepository.save(livro);

        return emprestimoRepository.save(emprestimo);
    }

    public Emprestimo devolver(Long id) {
        Emprestimo emprestimo = buscarPorId(id);

        emprestimo.setStatus(StatusEmprestimo.DEVOLVIDO);
        emprestimo.setDataDevolucaoEfetiva(LocalDate.now());

        Livro livro = emprestimo.getLivro();
        livro.setDisponivel(true);
        livroRepository.save(livro);

        return emprestimoRepository.save(emprestimo);
    }

    public void deletar(Long id) {
        buscarPorId(id);
        emprestimoRepository.deleteById(id);
    }
}
