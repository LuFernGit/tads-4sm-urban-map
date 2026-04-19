package br.com.senac.urbanmap.controllers.dtos;

import br.com.senac.urbanmap.entities.usuario.Funcao;
import br.com.senac.urbanmap.entities.usuario.Usuario;
import jakarta.validation.constraints.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.HashSet;


public record UsuarioCadastroDTO(
        @NotBlank(message = "O nome é obrigatório") @Size(max = 100) String nome,
        @NotBlank(message = "O nome de usuário é obrigatório") @Size(max = 100) String nomeUsuario,
        @NotBlank(message = "O email é obrigatório") @Email(message = "Email inválido") String email,
        @NotBlank(message = "A senha é obrigatória") String senha,
        @NotBlank(message = "O telefone é obrigatório") @Size(max = 15) String telefone,
        @NotNull(message = "A data de nascimento é obrigatória") LocalDate dataNascimento
) {
    public static Usuario converterParaUsuario(UsuarioCadastroDTO dto, PasswordEncoder passwordEncoder) {
        return Usuario
                .builder()
                .nome(dto.nome())
                .nomeUsuario(dto.nomeUsuario())
                .email(dto.email())
                .senha(passwordEncoder.encode(dto.senha()))
                .telefone(dto.telefone())
                .dataNascimento(dto.dataNascimento())
                .likes(new HashSet<>())
                .salvos(new HashSet<>())
                .funcao(Funcao.USUARIO)
                .status(true)
                .build();
    }
}