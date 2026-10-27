package br.com.biblioteca.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.biblioteca.app.model.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
}
