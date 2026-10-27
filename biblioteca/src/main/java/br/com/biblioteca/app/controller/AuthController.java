package br.com.biblioteca.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.biblioteca.app.dto.LoginRequestDTO;
import br.com.biblioteca.app.dto.LoginResponseDTO;
import br.com.biblioteca.app.dto.RegistroRequestDTO;
import br.com.biblioteca.app.dto.UsuarioResponseDTO;
import br.com.biblioteca.app.mapper.UsuarioMapper;
import br.com.biblioteca.app.model.PerfilUsuario;
import br.com.biblioteca.app.model.Usuario;
import br.com.biblioteca.app.security.TokenService;
import br.com.biblioteca.app.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Autenticação", description = "Endpoints de login e registro de usuários")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @PostMapping("/login")
    @Operation(summary = "Realiza login", description = "Autentica o usuário com email e senha e retorna um token JWT")
    @ApiResponse(responseCode = "200", description = "Login realizado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados de login inválidos")
    @ApiResponse(responseCode = "401", description = "Credenciais incorretas")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO dto) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getSenha());
        var authentication = authenticationManager.authenticate(authenticationToken);
        var usuario = (Usuario) authentication.getPrincipal();
        var tokenJWT = tokenService.gerarToken(usuario);
        return ResponseEntity.ok(new LoginResponseDTO(tokenJWT, "Bearer", usuario.getNome(), usuario.getEmail(), usuario.getPerfil()));
    }

    @PostMapping("/register")
    @Operation(summary = "Registra um novo usuário", description = "Cadastra um novo usuário no sistema")
    @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    public ResponseEntity<UsuarioResponseDTO> register(@Valid @RequestBody RegistroRequestDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        usuario.setPerfil(dto.getPerfil() != null ? dto.getPerfil() : PerfilUsuario.LEITOR);

        Usuario salvo = usuarioService.salvar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioMapper.toResponseDTO(salvo));
    }
}
