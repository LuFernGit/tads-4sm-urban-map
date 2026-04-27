package br.com.senac.urbanmap.controllers.dtos.usuario;

import br.com.senac.urbanmap.entities.usuario.Funcao;
import br.com.senac.urbanmap.entities.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.HashSet;


public record UsuarioCadastroDTO(
        @NotBlank(message = "Nome é obrigatório") @Size(max = 100) String nome,
        @NotBlank(message = "Nome de usuário é obrigatório") @Size(max = 100) String nomeUsuario,
        @NotBlank(message = "Email é obrigatório") @Email(message = "Email inválido") String email,
        @NotBlank(message = "Senha é obrigatório") String senha,
        @NotBlank(message = "Telefone é obrigatório") @Size(max = 15) String telefone,
        @NotNull(message = "Data de nascimento é obrigatório") @JsonFormat(pattern = "dd/MM/yyyy") LocalDate dataNascimento
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