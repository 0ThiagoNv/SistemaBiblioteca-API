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
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @Autowired
    private EmprestimoMapper emprestimoMapper;

    @GetMapping
    public List<EmprestimoResponseDTO> listarTodos() {
        return emprestimoService.listarTodos().stream()
                .map(emprestimoMapper::toResponseDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public EmprestimoResponseDTO buscarPorId(@PathVariable Long id) {
        return emprestimoMapper.toResponseDTO(emprestimoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<EmprestimoResponseDTO> criar(@Valid @RequestBody EmprestimoRequestDTO dto) {
        Emprestimo emprestimo = emprestimoService.criar(
                dto.getLivroId(),
                dto.getUsuarioId(),
                dto.getDataDevolucaoPrevista()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(emprestimoMapper.toResponseDTO(emprestimo));
    }

    @PutMapping("/{id}/devolver")
    public EmprestimoResponseDTO devolver(@PathVariable Long id) {
        return emprestimoMapper.toResponseDTO(emprestimoService.devolver(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        emprestimoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
