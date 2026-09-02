package br.com.biblioteca.app.dto;

import java.time.LocalDate;

import br.com.biblioteca.app.model.StatusEmprestimo;

public class EmprestimoResponseDTO {

    private Long id;
    private Long livroId;
    private String livroTitulo;
    private Long usuarioId;
    private String usuarioNome;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;
    private LocalDate dataDevolucaoEfetiva;
    private StatusEmprestimo status;

    public EmprestimoResponseDTO() {
    }

    public EmprestimoResponseDTO(Long id, Long livroId, String livroTitulo, Long usuarioId,
                                  String usuarioNome, LocalDate dataEmprestimo,
                                  LocalDate dataDevolucaoPrevista, LocalDate dataDevolucaoEfetiva,
                                  StatusEmprestimo status) {
        this.id = id;
        this.livroId = livroId;
        this.livroTitulo = livroTitulo;
        this.usuarioId = usuarioId;
        this.usuarioNome = usuarioNome;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
        this.dataDevolucaoEfetiva = dataDevolucaoEfetiva;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getLivroId() { return livroId; }
    public void setLivroId(Long livroId) { this.livroId = livroId; }

    public String getLivroTitulo() { return livroTitulo; }
    public void setLivroTitulo(String livroTitulo) { this.livroTitulo = livroTitulo; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public String getUsuarioNome() { return usuarioNome; }
    public void setUsuarioNome(String usuarioNome) { this.usuarioNome = usuarioNome; }

    public LocalDate getDataEmprestimo() { return dataEmprestimo; }
    public void setDataEmprestimo(LocalDate dataEmprestimo) { this.dataEmprestimo = dataEmprestimo; }

    public LocalDate getDataDevolucaoPrevista() { return dataDevolucaoPrevista; }
    public void setDataDevolucaoPrevista(LocalDate dataDevolucaoPrevista) { this.dataDevolucaoPrevista = dataDevolucaoPrevista; }

    public LocalDate getDataDevolucaoEfetiva() { return dataDevolucaoEfetiva; }
    public void setDataDevolucaoEfetiva(LocalDate dataDevolucaoEfetiva) { this.dataDevolucaoEfetiva = dataDevolucaoEfetiva; }

    public StatusEmprestimo getStatus() { return status; }
    public void setStatus(StatusEmprestimo status) { this.status = status; }
}
