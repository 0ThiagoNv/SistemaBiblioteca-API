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

import br.com.biblioteca.app.dto.CategoriaRequestDTO;
import br.com.biblioteca.app.dto.CategoriaResponseDTO;
import br.com.biblioteca.app.mapper.CategoriaMapper;
import br.com.biblioteca.app.model.Categoria;
import br.com.biblioteca.app.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categorias")
@Tag(name = "Categorias", description = "Gerenciamento de categorias de livros")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private CategoriaMapper categoriaMapper;

    @GetMapping
    @Operation(summary = "Listar todas as categorias", description = "Retorna uma lista com todas as categorias cadastradas")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @ApiResponse(responseCode = "401", description = "Não autenticado")
    public List<CategoriaResponseDTO> listarTodos() {
        return categoriaService.listarTodos().stream()
                .map(categoriaMapper::toResponseDTO)
                .toList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar categoria por ID", description = "Retorna os detalhes de uma categoria específica")
    @ApiResponse(responseCode = "200", description = "Categoria encontrada")
    @ApiResponse(responseCode = "401", description = "Não autenticado")
    @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    public CategoriaResponseDTO buscarPorId(@PathVariable Long id) {
        return categoriaMapper.toResponseDTO(categoriaService.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Criar nova categoria", description = "Cadastra uma nova categoria (apenas ADMIN)")
    @ApiResponse(responseCode = "201", description = "Categoria criada com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    @ApiResponse(responseCode = "401", description = "Não autenticado")
    @ApiResponse(responseCode = "403", description = "Acesso negado - requer perfil ADMIN")
    public ResponseEntity<CategoriaResponseDTO> criar(@Valid @RequestBody CategoriaRequestDTO dto) {
        Categoria categoria = categoriaService.salvar(categoriaMapper.toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaMapper.toResponseDTO(categoria));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar categoria", description = "Atualiza os dados de uma categoria existente (apenas ADMIN)")
    @ApiResponse(responseCode = "200", description = "Categoria atualizada com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    @ApiResponse(responseCode = "401", description = "Não autenticado")
    @ApiResponse(responseCode = "403", description = "Acesso negado - requer perfil ADMIN")
    @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    public CategoriaResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody CategoriaRequestDTO dto) {
        Categoria categoria = categoriaService.atualizar(id, categoriaMapper.toEntity(dto));
        return categoriaMapper.toResponseDTO(categoria);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir categoria", description = "Remove uma categoria do sistema (apenas ADMIN)")
    @ApiResponse(responseCode = "204", description = "Categoria removida com sucesso")
    @ApiResponse(responseCode = "401", description = "Não autenticado")
    @ApiResponse(responseCode = "403", description = "Acesso negado - requer perfil ADMIN")
    @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        categoriaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
