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

import br.com.biblioteca.app.dto.EmprestimoRequestDTO;
import br.com.biblioteca.app.dto.EmprestimoResponseDTO;
import br.com.biblioteca.app.mapper.EmprestimoMapper;
import br.com.biblioteca.app.model.Emprestimo;
import br.com.biblioteca.app.service.EmprestimoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/emprestimos")
@Tag(name = "Empréstimos", description = "Gerenciamento e controle do ciclo de vida dos empréstimos de livros")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @Autowired
    private EmprestimoMapper emprestimoMapper;

    @GetMapping
    @Operation(summary = "Listar todos os empréstimos", description = "Retorna o histórico completo de empréstimos")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @ApiResponse(responseCode = "401", description = "Não autenticado")
    public List<EmprestimoResponseDTO> listarTodos() {
        return emprestimoService.listarTodos().stream()
                .map(emprestimoMapper::toResponseDTO)
                .toList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar empréstimo por ID", description = "Retorna os detalhes de um empréstimo específico")
    @ApiResponse(responseCode = "200", description = "Empréstimo encontrado")
    @ApiResponse(responseCode = "401", description = "Não autenticado")
    @ApiResponse(responseCode = "404", description = "Empréstimo não encontrado")
    public EmprestimoResponseDTO buscarPorId(@PathVariable Long id) {
        return emprestimoMapper.toResponseDTO(emprestimoService.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Realizar novo empréstimo", description = "Registra a locação de um livro para um usuário (liberado para qualquer usuário logado)")
    @ApiResponse(responseCode = "201", description = "Empréstimo realizado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    @ApiResponse(responseCode = "401", description = "Não autenticado")
    @ApiResponse(responseCode = "404", description = "Livro ou Usuário não encontrados")
    @ApiResponse(responseCode = "409", description = "Livro já está emprestado")
    public ResponseEntity<EmprestimoResponseDTO> criar(@Valid @RequestBody EmprestimoRequestDTO dto) {
        Emprestimo emprestimo = emprestimoService.criar(
                dto.getLivroId(),
                dto.getUsuarioId(),
                dto.getDataDevolucaoPrevista()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(emprestimoMapper.toResponseDTO(emprestimo));
    }

    @PutMapping("/{id}/devolver")
    @Operation(summary = "Devolver livro", description = "Registra a devolução do livro e altera seu status para disponível (liberado para qualquer usuário logado)")
    @ApiResponse(responseCode = "200", description = "Devolução registrada com sucesso")
    @ApiResponse(responseCode = "401", description = "Não autenticado")
    @ApiResponse(responseCode = "404", description = "Empréstimo não encontrado")
    public EmprestimoResponseDTO devolver(@PathVariable Long id) {
        return emprestimoMapper.toResponseDTO(emprestimoService.devolver(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir empréstimo", description = "Remove um registro de empréstimo (apenas ADMIN)")
    @ApiResponse(responseCode = "204", description = "Empréstimo excluído com sucesso")
    @ApiResponse(responseCode = "401", description = "Não autenticado")
    @ApiResponse(responseCode = "403", description = "Acesso negado - requer perfil ADMIN")
    @ApiResponse(responseCode = "404", description = "Empréstimo não encontrado")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        emprestimoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
