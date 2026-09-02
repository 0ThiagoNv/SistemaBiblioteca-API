package br.com.biblioteca.app.dto;

import jakarta.validation.constraints.NotBlank;

public class AutorRequestDTO {

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    private String nacionalidade;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getNacionalidade() { return nacionalidade; }
    public void setNacionalidade(String nacionalidade) { this.nacionalidade = nacionalidade; }
}
