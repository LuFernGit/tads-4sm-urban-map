package br.com.senac.urbanmap.controllers.dtos.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioLoginDTO(
        @NotBlank(message = "Email é obrigatório") @Email(message = "o e-mail deve ser válido") String email,
        @NotBlank(message = "Senha é obrigatório") String senha) {
}