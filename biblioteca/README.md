# 📚 API de Gerenciamento de Biblioteca

Projeto acadêmico de uma **API RESTful completa** desenvolvida em **Java 21** e **Spring Boot 3.4.0**, com banco de dados em memória **H2**, versionamento de banco com **Flyway**, segurança e autenticação com **JWT + Spring Security**, documentação interativa com **Swagger (OpenAPI)** e tela web em HTML/JS puro.

---

## 🗺️ Mapa Visual das Pastas (Para você se guiar facilmente)

A estrutura do projeto foi organizada seguindo as boas práticas corporativas e a arquitetura em camadas do Spring Boot:

```text
biblioteca/
│
├── 📁 docs/                                   # 📖 Todos os documentos de estudo e apresentação
│   ├── GUIA_APRESENTACAO_SIMPLES.md           # 👈 LEIA ESTE: Guia simples com analogias e respostas da banca
│   └── DOCUMENTACAO_COMPLETA_TECNICA.md       # Documentação detalhada e teórica original
│
├── 📁 src/
│   └── 📁 main/
│       ├── 📁 java/br/com/biblioteca/app/     # ☕ Todo o código Java da aplicação (Backend)
│       │   │
│       │   ├── BibliotecaApplication.java     # Classe principal que inicia o Spring Boot
│       │   │
│       │   ├── 📁 config/                     # Configurações do sistema
│       │   │   └── OpenApiConfig.java         # Configuração do Swagger com o botão Authorize (JWT)
│       │   │
│       │   ├── 📁 controller/                 # 🚪 A Porta de Entrada (Os Garçons que atendem a web)
│       │   │   ├── AuthController.java        # Endpoints de login (/api/auth/login) e cadastro (/api/auth/register)
│       │   │   ├── AutorController.java       # CRUD de Autores (/api/autores)
│       │   │   ├── CategoriaController.java   # CRUD de Categorias (/api/categorias)
│       │   │   ├── LivroController.java       # CRUD de Livros (/api/livros)
│       │   │   ├── UsuarioController.java     # CRUD de Usuários (/api/usuarios)
│       │   │   └── EmprestimoController.java  # Ciclo de Empréstimos e Devoluções (/api/emprestimos)
│       │   │
│       │   ├── 📁 service/                    # 🧠 O Cérebro (Onde ficam as Regras de Negócio)
│       │   │   ├── AutorService.java
│       │   │   ├── CategoriaService.java
│       │   │   ├── LivroService.java
│       │   │   ├── UsuarioService.java        # Codificação de senha com BCrypt e perfil padrão
│       │   │   └── EmprestimoService.java     # Regra que impede emprestar livro já alugado
│       │   │
│       │   ├── 📁 repository/                 # 🗄️ Acesso ao Banco de Dados (Spring Data JPA)
│       │   │   ├── AutorRepository.java
│       │   │   ├── CategoriaRepository.java
│       │   │   ├── LivroRepository.java
│       │   │   ├── UsuarioRepository.java     # Busca de usuário por email (findByEmail)
│       │   │   └── EmprestimoRepository.java
│       │   │
│       │   ├── 📁 model/                      # 📦 Entidades do Banco (Tabelas espelhadas em Java)
│       │   │   ├── Autor.java                 # Tabela 'autor'
│       │   │   ├── Categoria.java             # Tabela 'categoria'
│       │   │   ├── Livro.java                 # Tabela 'livro' (ligada a Autor e Categoria)
│       │   │   ├── Usuario.java               # Tabela 'usuario' (com senha BCrypt e perfil ADMIN/LEITOR)
│       │   │   ├── Emprestimo.java            # Tabela 'emprestimo'
│       │   │   ├── PerfilUsuario.java         # Enum: ADMIN, LEITOR
│       │   │   └── StatusEmprestimo.java      # Enum: ATIVO, DEVOLVIDO
│       │   │
│       │   ├── 📁 dto/                        # 📋 Fichas de Dados (RequestDTO entrada / ResponseDTO saída)
│       │   │   ├── AutorRequestDTO.java / AutorResponseDTO.java
│       │   │   ├── CategoriaRequestDTO.java / CategoriaResponseDTO.java
│       │   │   ├── LivroRequestDTO.java / LivroResponseDTO.java
│       │   │   ├── UsuarioRequestDTO.java / UsuarioResponseDTO.java (nunca expõe a senha!)
│       │   │   ├── EmprestimoRequestDTO.java / EmprestimoResponseDTO.java
│       │   │   └── LoginRequestDTO.java / LoginResponseDTO.java / RegistroRequestDTO.java
│       │   │
│       │   ├── 📁 mapper/                     # 🔄 Tradutores (Convertem DTO em Entidade e vice-versa)
│       │   │   ├── AutorMapper.java
│       │   │   ├── CategoriaMapper.java
│       │   │   ├── LivroMapper.java
│       │   │   ├── UsuarioMapper.java
│       │   │   └── EmprestimoMapper.java
│       │   │
│       │   ├── 📁 security/                   # 🛡️ Segurança e Autenticação (Spring Security + JWT)
│       │   │   ├── TokenService.java          # Cria e valida o token JWT com chave secreta
│       │   │   ├── SecurityFilter.java        # Filtro que lê o cabeçalho 'Authorization: Bearer'
│       │   │   ├── SecurityConfigurations.java# Define quais rotas são públicas e quais exigem ADMIN
│       │   │   └── AutenticacaoService.java   # Carrega o usuário do banco pelo email no login
│       │   │
│       │   └── 📁 exception/                  # 🚨 Tratamento Global de Erros
│       │       ├── ApiExceptionHandler.java   # Intercepta erros e devolve JSON bonito (400, 401, 403, 404, 409)
│       │       ├── ErroResponseDTO.java       # Formato padrão do erro retornado
│       │       ├── LivroIndisponivelException.java
│       │       └── RecursoNaoEncontradoException.java
│       │
│       └── 📁 resources/                      # ⚙️ Configurações, Migrations e Telas
│           ├── application.properties         # Configurações do H2, Flyway (ddl-auto=validate) e porta 8080
│           │
│           ├── 📁 db/migration/               # 📜 Migrations do Flyway (Versionamento SQL do banco)
│           │   ├── V1__criar_tabelas.sql      # Cria as 5 tabelas com chaves primárias e estrangeiras
│           │   └── V2__inserir_dados_iniciais.sql # Insere dados iniciais e usuários com BCrypt
│           │
│           └── 📁 static/                     # 🌐 Tela Web do Sistema
│               └── index.html                 # Interface visual simples com formulário de login e catálogo
│
├── .gitignore                                 # Impede que pastas temporárias (target, .idea) subam pro Git
└── pom.xml                                    # Dependências Maven (Security, JWT, Flyway, Swagger, JPA)
```

---

## 🧭 Guia Rápido: "Onde eu mexo se eu quiser..."

| O que você quer fazer? | Qual pasta você deve abrir? |
| :--- | :--- |
| **Estudar para a apresentação / entender o projeto** | Abra a pasta `docs/` e leia o `GUIA_APRESENTACAO_SIMPLES.md`. |
| **Ver ou alterar as rotas da API (URLs e verbos HTTP)** | Abra `src/main/java/br/com/biblioteca/app/controller/`. |
| **Ver ou alterar regras de negócio (ex: validações de empréstimo)** | Abra `src/main/java/br/com/biblioteca/app/service/`. |
| **Ver ou alterar as tabelas ou campos do banco de dados** | Veja as entidades em `model/` e os scripts SQL em `src/main/resources/db/migration/`. |
| **Ver como o login, JWT ou permissões funcionam** | Abra `src/main/java/br/com/biblioteca/app/security/`. |
| **Ver ou mudar o visual da tela do navegador** | Abra `src/main/resources/static/index.html`. |
| **Ver as mensagens de erro devolvidas para o cliente** | Abra `src/main/java/br/com/biblioteca/app/exception/`. |
| **Adicionar novas dependências no projeto** | Abra o arquivo `pom.xml` na raiz. |

---

## 🚀 Como Executar o Projeto

1. Abra o terminal na pasta raiz do projeto (`c:\Users\devth\Downloads\biblioteca (2)\biblioteca`).
2. Execute o comando:
   ```bash
   mvn spring-boot:run
   ```
3. A aplicação subirá na porta **8080**.

---

## 🌐 Links Rápidos após Iniciar a Aplicação

- **Tela Web do Sistema:** [http://localhost:8080](http://localhost:8080)
- **Swagger UI (Documentação Interativa):** [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
- **Console do Banco H2:** [http://localhost:8080/h2-console](http://localhost:8080/h2-console)  
  *(JDBC URL: `jdbc:h2:mem:biblioteca` | Usuário: `sa` | Senha: em branco)*

---

## 🔑 Usuários para Teste

| Perfil | E-mail | Senha | Permissões |
| :--- | :--- | :--- | :--- |
| **ADMIN** | `admin@biblioteca.com` | `admin123` | **Acesso total:** pode cadastrar, editar e excluir livros, autores, categorias e usuários, além de emprestar e devolver. |
| **LEITOR** | `leitor@biblioteca.com` | `leitor123` | **Acesso restrito:** consulta livros e pode pegar emprestado ou devolver. Bloqueado de criar ou apagar cadastros (retorna 403 Forbidden). |
| **LEITORA (Ana)** | `ana.souza@email.com` | `senha123` | Usuária da carga inicial com livro já emprestado para teste de devolução. |
