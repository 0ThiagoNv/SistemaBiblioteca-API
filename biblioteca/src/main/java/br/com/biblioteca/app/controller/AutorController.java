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

import br.com.biblioteca.app.dto.AutorRequestDTO;
import br.com.biblioteca.app.dto.AutorResponseDTO;
import br.com.biblioteca.app.mapper.AutorMapper;
import br.com.biblioteca.app.model.Autor;
import br.com.biblioteca.app.service.AutorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/autores")
@Tag(name = "Autores", description = "Gerenciamento de autores de livros")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @Autowired
    private AutorMapper autorMapper;

    @GetMapping
    @Operation(summary = "Listar todos os autores", description = "Retorna uma lista de todos os autores cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @ApiResponse(responseCode = "401", description = "Não autenticado")
    public List<AutorResponseDTO> listarTodos() {
        return autorService.listarTodos().stream()
                .map(autorMapper::toResponseDTO)
                .toList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar autor por ID", description = "Retorna os detalhes de um autor específico")
    @ApiResponse(responseCode = "200", description = "Autor encontrado")
    @ApiResponse(responseCode = "401", description = "Não autenticado")
    @ApiResponse(responseCode = "404", description = "Autor não encontrado")
    public AutorResponseDTO buscarPorId(@PathVariable Long id) {
        return autorMapper.toResponseDTO(autorService.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Criar novo autor", description = "Cadastra um novo autor no catálogo (apenas ADMIN)")
    @ApiResponse(responseCode = "201", description = "Autor criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    @ApiResponse(responseCode = "401", description = "Não autenticado")
    @ApiResponse(responseCode = "403", description = "Acesso negado - requer perfil ADMIN")
    public ResponseEntity<AutorResponseDTO> criar(@Valid @RequestBody AutorRequestDTO dto) {
        Autor autor = autorService.salvar(autorMapper.toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(autorMapper.toResponseDTO(autor));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar autor", description = "Atualiza os dados de um autor existente (apenas ADMIN)")
    @ApiResponse(responseCode = "200", description = "Autor atualizado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    @ApiResponse(responseCode = "401", description = "Não autenticado")
    @ApiResponse(responseCode = "403", description = "Acesso negado - requer perfil ADMIN")
    @ApiResponse(responseCode = "404", description = "Autor não encontrado")
    public AutorResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody AutorRequestDTO dto) {
        Autor autor = autorService.atualizar(id, autorMapper.toEntity(dto));
        return autorMapper.toResponseDTO(autor);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir autor", description = "Remove um autor do catálogo (apenas ADMIN)")
    @ApiResponse(responseCode = "204", description = "Autor excluído com sucesso")
    @ApiResponse(responseCode = "401", description = "Não autenticado")
    @ApiResponse(responseCode = "403", description = "Acesso negado - requer perfil ADMIN")
    @ApiResponse(responseCode = "404", description = "Autor não encontrado")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        autorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
