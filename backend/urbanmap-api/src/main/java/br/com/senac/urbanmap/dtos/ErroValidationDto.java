package br.com.senac.urbanmap.dtos;

import org.springframework.validation.FieldError;


// especifico para o validation
public record ErroValidationDto(String campo, String mensagem) {
    public ErroValidationDto(FieldError erro) {
        this(erro.getField(), erro.getDefaultMessage());
    }
}