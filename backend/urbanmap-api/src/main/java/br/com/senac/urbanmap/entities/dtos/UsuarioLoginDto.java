package br.com.senac.urbanmap.entities.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioLoginDto(
        @NotBlank @Email String email,
        @NotBlank String senha) {
}
