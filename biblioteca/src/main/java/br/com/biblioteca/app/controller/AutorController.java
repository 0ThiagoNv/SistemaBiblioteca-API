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
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @Autowired
    private AutorMapper autorMapper;

    @GetMapping
    public List<AutorResponseDTO> listarTodos() {
        return autorService.listarTodos().stream()
                .map(autorMapper::toResponseDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public AutorResponseDTO buscarPorId(@PathVariable Long id) {
        return autorMapper.toResponseDTO(autorService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<AutorResponseDTO> criar(@Valid @RequestBody AutorRequestDTO dto) {
        Autor autor = autorService.salvar(autorMapper.toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(autorMapper.toResponseDTO(autor));
    }

    @PutMapping("/{id}")
    public AutorResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody AutorRequestDTO dto) {
        Autor autor = autorService.atualizar(id, autorMapper.toEntity(dto));
        return autorMapper.toResponseDTO(autor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        autorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
