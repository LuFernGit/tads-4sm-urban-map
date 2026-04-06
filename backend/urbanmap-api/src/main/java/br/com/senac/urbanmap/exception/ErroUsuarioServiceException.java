package br.com.senac.urbanmap.exception;


public class ErroUsuarioServiceException extends RuntimeException {
    public ErroUsuarioServiceException(String mensagem) {
        super(mensagem);
    }
}