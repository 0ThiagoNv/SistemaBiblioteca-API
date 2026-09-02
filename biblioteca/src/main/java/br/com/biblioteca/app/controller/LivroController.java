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
import br.com.biblioteca.app.model.Livro;
import br.com.biblioteca.app.service.LivroService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @Autowired
    private LivroMapper livroMapper;

    @GetMapping
    public List<LivroResponseDTO> listarTodos() {
        return livroService.listarTodos().stream()
                .map(livroMapper::toResponseDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public LivroResponseDTO buscarPorId(@PathVariable Long id) {
        return livroMapper.toResponseDTO(livroService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<LivroResponseDTO> criar(@Valid @RequestBody LivroRequestDTO dto) {
        Autor autor = livroService.buscarAutor(dto.getAutorId());
        Livro livro = livroService.salvar(livroMapper.toEntity(dto, autor));
        return ResponseEntity.status(HttpStatus.CREATED).body(livroMapper.toResponseDTO(livro));
    }

    @PutMapping("/{id}")
    public LivroResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody LivroRequestDTO dto) {
        Autor autor = livroService.buscarAutor(dto.getAutorId());
        Livro livro = livroService.atualizar(id, livroMapper.toEntity(dto, autor));
        return livroMapper.toResponseDTO(livro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        livroService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
