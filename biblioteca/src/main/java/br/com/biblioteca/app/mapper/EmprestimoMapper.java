package br.com.biblioteca.app.mapper;

import org.springframework.stereotype.Component;

import br.com.biblioteca.app.dto.EmprestimoResponseDTO;
import br.com.biblioteca.app.model.Emprestimo;

@Component
public class EmprestimoMapper {

    public EmprestimoResponseDTO toResponseDTO(Emprestimo emprestimo) {
        return new EmprestimoResponseDTO(
                emprestimo.getId(),
                emprestimo.getLivro() != null ? emprestimo.getLivro().getId() : null,
                emprestimo.getLivro() != null ? emprestimo.getLivro().getTitulo() : null,
                emprestimo.getUsuario() != null ? emprestimo.getUsuario().getId() : null,
                emprestimo.getUsuario() != null ? emprestimo.getUsuario().getNome() : null,
                emprestimo.getDataEmprestimo(),
                emprestimo.getDataDevolucaoPrevista(),
                emprestimo.getDataDevolucaoEfetiva(),
                emprestimo.getStatus()
        );
    }
}
