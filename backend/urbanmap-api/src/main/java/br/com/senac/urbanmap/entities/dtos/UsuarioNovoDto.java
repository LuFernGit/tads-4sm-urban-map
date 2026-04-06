package br.com.senac.urbanmap.entities.dtos;

import jakarta.validation.constraints.*;

import java.time.LocalDate;


public record UsuarioNovoDto(@NotBlank(message = "O nome é obrigatório") @Size(max = 100) String nome,
                             @NotBlank(message = "O nome de usuário é obrigatório") @Size(max = 100) String nomeUsuario,
                             @NotNull(message = "A data de nascimento é obrigatória") LocalDate dataNascimento,
                             @NotBlank(message = "O email é obrigatório") @Email(message = "Email inválido") String email,
                             @NotBlank(message = "A senha é obrigatória") String senha,
                             @NotBlank(message = "O telefone é obrigatório") @Size(max = 15) String telefone) {
}