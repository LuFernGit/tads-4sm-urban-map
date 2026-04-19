package br.com.senac.urbanmap.controllers.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioLoginDTO(
        @NotBlank(message = "o campo 'email' é obrigatório") @Email(message = "o e-mail deve ser válido") String email,
        @NotBlank(message = "o campo 'senha' é obrigatório") String senha) {
}