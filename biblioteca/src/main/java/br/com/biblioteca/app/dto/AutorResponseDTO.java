package br.com.biblioteca.app.dto;

public class AutorResponseDTO {

    private Long id;
    private String nome;
    private String nacionalidade;

    public AutorResponseDTO() {
    }

    public AutorResponseDTO(Long id, String nome, String nacionalidade) {
        this.id = id;
        this.nome = nome;
        this.nacionalidade = nacionalidade;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getNacionalidade() { return nacionalidade; }
    public void setNacionalidade(String nacionalidade) { this.nacionalidade = nacionalidade; }
}
