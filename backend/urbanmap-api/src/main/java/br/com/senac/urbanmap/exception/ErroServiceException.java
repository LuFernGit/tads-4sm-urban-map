package br.com.senac.urbanmap.exception;


public class ErroServiceException extends RuntimeException {
    public ErroServiceException(String mensagem) {
        super(mensagem);
    }
}