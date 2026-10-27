package br.com.biblioteca.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.biblioteca.app.dto.LivroRequestDTO;
import br.com.biblioteca.app.dto.LivroResponseDTO;
import br.com.biblioteca.app.mapper.LivroMapper;
import br.com.biblioteca.app.model.Autor;
import br.com.biblioteca.app.model.Categoria;
import br.com.biblioteca.app.model.Livro;
import br.com.biblioteca.app.service.LivroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/livros")
@Tag(name = "Livros", description = "Gerenciamento do catálogo de livros da biblioteca")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @Autowired
    private LivroMapper livroMapper;

    @GetMapping
    @Operation(summary = "Listar todos os livros", description = "Retorna uma lista com todos os livros e suas disponibilidades")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @ApiResponse(responseCode = "401", description = "Não autenticado")
    public List<LivroResponseDTO> listarTodos() {
        return livroService.listarTodos().stream()
                .map(livroMapper::toResponseDTO)
                .toList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar livro por ID", description = "Retorna os detalhes de um livro específico")
    @ApiResponse(responseCode = "200", description = "Livro encontrado")
    @ApiResponse(responseCode = "401", description = "Não autenticado")
    @ApiResponse(responseCode = "404", description = "Livro não encontrado")
    public LivroResponseDTO buscarPorId(@PathVariable Long id) {
        return livroMapper.toResponseDTO(livroService.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Criar novo livro", description = "Cadastra um novo livro no catálogo (apenas ADMIN)")
    @ApiResponse(responseCode = "201", description = "Livro cadastrado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    @ApiResponse(responseCode = "401", description = "Não autenticado")
    @ApiResponse(responseCode = "403", description = "Acesso negado - requer perfil ADMIN")
    @ApiResponse(responseCode = "404", description = "Autor ou Categoria não encontrados")
    public ResponseEntity<LivroResponseDTO> criar(@Valid @RequestBody LivroRequestDTO dto) {
        Autor autor = livroService.buscarAutor(dto.getAutorId());
        Categoria categoria = livroService.buscarCategoria(dto.getCategoriaId());
        Livro livro = livroService.salvar(livroMapper.toEntity(dto, autor, categoria));
        return ResponseEntity.status(HttpStatus.CREATED).body(livroMapper.toResponseDTO(livro));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar livro", description = "Atualiza os dados de um livro existente (apenas ADMIN)")
    @ApiResponse(responseCode = "200", description = "Livro atualizado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    @ApiResponse(responseCode = "401", description = "Não autenticado")
    @ApiResponse(responseCode = "403", description = "Acesso negado - requer perfil ADMIN")
    @ApiResponse(responseCode = "404", description = "Livro, Autor ou Categoria não encontrados")
    public LivroResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody LivroRequestDTO dto) {
        Autor autor = livroService.buscarAutor(dto.getAutorId());
        Categoria categoria = livroService.buscarCategoria(dto.getCategoriaId());
        Livro livro = livroService.atualizar(id, livroMapper.toEntity(dto, autor, categoria));
        return livroMapper.toResponseDTO(livro);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir livro", description = "Remove um livro do catálogo (apenas ADMIN)")
    @ApiResponse(responseCode = "204", description = "Livro excluído com sucesso")
    @ApiResponse(responseCode = "401", description = "Não autenticado")
    @ApiResponse(responseCode = "403", description = "Acesso negado - requer perfil ADMIN")
    @ApiResponse(responseCode = "404", description = "Livro não encontrado")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        livroService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
