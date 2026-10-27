package br.com.biblioteca.app.dto;

import br.com.biblioteca.app.model.PerfilUsuario;

public class LoginResponseDTO {

    private String token;
    private String tipo = "Bearer";
    private String nome;
    private String email;
    private PerfilUsuario perfil;

    public LoginResponseDTO() {
    }

    public LoginResponseDTO(String token, String tipo, String nome, String email, PerfilUsuario perfil) {
        this.token = token;
        this.tipo = tipo;
        this.nome = nome;
        this.email = email;
        this.perfil = perfil;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PerfilUsuario getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilUsuario perfil) {
        this.perfil = perfil;
    }
}
