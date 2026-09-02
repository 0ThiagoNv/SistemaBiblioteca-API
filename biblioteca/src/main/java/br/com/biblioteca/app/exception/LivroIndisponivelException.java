package br.com.biblioteca.app.exception;

public class LivroIndisponivelException extends RuntimeException {

    public LivroIndisponivelException(String mensagem) {
        super(mensagem);
    }
}
