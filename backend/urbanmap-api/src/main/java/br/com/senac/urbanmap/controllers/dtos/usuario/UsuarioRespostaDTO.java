package br.com.senac.urbanmap.controllers.dtos.usuario;

import br.com.senac.urbanmap.entities.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public record UsuarioRespostaDTO(
        Long id,
        String nome,
        String nomeUsuario,
        String fotoUrl,
        String email,
        String telefone,
        @JsonFormat(pattern = "dd/MM/yyyy") LocalDate dataNascimento,
        Set<Long> likes,
        Set<Long> salvos,
        boolean Status,
        String funcao,
        String token
) {
    public static UsuarioRespostaDTO converterParaDTO(Usuario usuario, String token) {
        Set<Long> likes = new HashSet<>();
        Set<Long> salvos = new HashSet<>();
        usuario.getLikes().forEach(local -> likes.add(local.getId()));
        usuario.getSalvos().forEach(local -> salvos.add((local.getId())));
        return new UsuarioRespostaDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getNomeUsuario(),
                usuario.getFotoUrl(),
                usuario.getEmail(),
                usuario.getTelefone(),
                usuario.getDataNascimento(),
                likes,
                salvos,
                usuario.getStatus(),
                usuario.getFuncao().getTipo(),
                token);
    }
}