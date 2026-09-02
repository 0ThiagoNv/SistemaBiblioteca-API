package br.com.biblioteca.app.config;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.biblioteca.app.model.Autor;
import br.com.biblioteca.app.model.Emprestimo;
import br.com.biblioteca.app.model.Livro;
import br.com.biblioteca.app.model.Usuario;
import br.com.biblioteca.app.repository.AutorRepository;
import br.com.biblioteca.app.repository.LivroRepository;
import br.com.biblioteca.app.repository.UsuarioRepository;
import br.com.biblioteca.app.service.EmprestimoService;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmprestimoService emprestimoService;

    @Override
    public void run(String... args) {
        if (autorRepository.count() > 0) {
            return;
        }

        Autor colleenHoover = criarAutor("Colleen Hoover", "Estados Unidos");
        Autor mattHaig = criarAutor("Matt Haig", "Reino Unido");
        Autor nitaProse = criarAutor("Nita Prose", "Canadá");
        Autor garciaMarquez = criarAutor("Gabriel García Márquez", "Colômbia");

        Livro verity = criarLivro("Verity", "978-85-508-0700-4", 2018, colleenHoover);
        criarLivro("É Assim que Acaba", "978-85-508-1060-8", 2016, colleenHoover);
        Livro bibliotecaMeiaNoite = criarLivro("A Biblioteca da Meia-Noite", "978-65-5511-175-6", 2020, mattHaig);
        Livro aEmpregada = criarLivro("A Empregada", "978-65-5565-305-7", 2022, nitaProse);
        criarLivro("A Empregada em Fúria", "978-65-5565-647-8", 2023, nitaProse);
        criarLivro("Cem Anos de Solidão", "978-85-01-06367-2", 1967, garciaMarquez);

        Usuario ana = criarUsuario("Ana Beatriz Souza", "ana.souza@email.com");
        Usuario bruno = criarUsuario("Bruno Carvalho", "bruno.carvalho@email.com");
        Usuario carla = criarUsuario("Carla Menezes", "carla.menezes@email.com");
        criarUsuario("Diego Ferreira", "diego.ferreira@email.com");

        emprestimoService.criar(verity.getId(), ana.getId(), LocalDate.now().plusDays(7));
        emprestimoService.criar(bibliotecaMeiaNoite.getId(), bruno.getId(), LocalDate.now().plusDays(10));

        Emprestimo emprestimoDevolvido = emprestimoService.criar(aEmpregada.getId(), carla.getId(), LocalDate.now().minusDays(3));
        emprestimoService.devolver(emprestimoDevolvido.getId());
    }

    private Autor criarAutor(String nome, String nacionalidade) {
        Autor autor = new Autor();
        autor.setNome(nome);
        autor.setNacionalidade(nacionalidade);
        return autorRepository.save(autor);
    }

    private Livro criarLivro(String titulo, String isbn, int anoPublicacao, Autor autor) {
        Livro livro = new Livro();
        livro.setTitulo(titulo);
        livro.setIsbn(isbn);
        livro.setAnoPublicacao(anoPublicacao);
        livro.setAutor(autor);
        livro.setDisponivel(true);
        return livroRepository.save(livro);
    }

    private Usuario criarUsuario(String nome, String email) {
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        return usuarioRepository.save(usuario);
    }
}
