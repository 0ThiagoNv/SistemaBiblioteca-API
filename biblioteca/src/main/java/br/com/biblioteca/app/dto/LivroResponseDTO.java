package br.com.biblioteca.app.dto;

public class LivroResponseDTO {

    private Long id;
    private String titulo;
    private String isbn;
    private Integer anoPublicacao;
    private boolean disponivel;
    private Long autorId;
    private String autorNome;
    private Long categoriaId;
    private String categoriaNome;

    public LivroResponseDTO() {
    }

    public LivroResponseDTO(Long id, String titulo, String isbn, Integer anoPublicacao,
                            boolean disponivel, Long autorId, String autorNome) {
        this(id, titulo, isbn, anoPublicacao, disponivel, autorId, autorNome, null, null);
    }

    public LivroResponseDTO(Long id, String titulo, String isbn, Integer anoPublicacao,
                            boolean disponivel, Long autorId, String autorNome,
                            Long categoriaId, String categoriaNome) {
        this.id = id;
        this.titulo = titulo;
        this.isbn = isbn;
        this.anoPublicacao = anoPublicacao;
        this.disponivel = disponivel;
        this.autorId = autorId;
        this.autorNome = autorNome;
        this.categoriaId = categoriaId;
        this.categoriaNome = categoriaNome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(Integer anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public Long getAutorId() {
        return autorId;
    }

    public void setAutorId(Long autorId) {
        this.autorId = autorId;
    }

    public String getAutorNome() {
        return autorNome;
    }

    public void setAutorNome(String autorNome) {
        this.autorNome = autorNome;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getCategoriaNome() {
        return categoriaNome;
    }

    public void setCategoriaNome(String categoriaNome) {
        this.categoriaNome = categoriaNome;
    }
}
