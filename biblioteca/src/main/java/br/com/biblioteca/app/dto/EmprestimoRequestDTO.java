package br.com.biblioteca.app.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public class EmprestimoRequestDTO {

    @NotNull(message = "O livro é obrigatório")
    private Long livroId;

    @NotNull(message = "O usuário é obrigatório")
    private Long usuarioId;

    @NotNull(message = "A data de devolução prevista é obrigatória")
    private LocalDate dataDevolucaoPrevista;

    public Long getLivroId() { return livroId; }
    public void setLivroId(Long livroId) { this.livroId = livroId; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public LocalDate getDataDevolucaoPrevista() { return dataDevolucaoPrevista; }
    public void setDataDevolucaoPrevista(LocalDate dataDevolucaoPrevista) { this.dataDevolucaoPrevista = dataDevolucaoPrevista; }
}
