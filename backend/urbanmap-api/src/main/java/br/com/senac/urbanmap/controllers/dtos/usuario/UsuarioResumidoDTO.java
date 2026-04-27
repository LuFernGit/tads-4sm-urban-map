package br.com.senac.urbanmap.controllers.dtos.usuario;

import br.com.senac.urbanmap.entities.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public record UsuarioResumidoDTO(
        Long id,
        String nome,
        String nomeUsuario,
        String fotoUrl,
        String email,
        String telefone,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataNascimento
) {
    public static UsuarioResumidoDTO conveterParaDTO(Usuario u) {
        return new UsuarioResumidoDTO(
                u.getId(),
                u.getNome(),
                u.getNomeUsuario(),
                u.getFotoUrl(),
                u.getEmail(),
                u.getTelefone(),
                u.getDataNascimento()
        );
    }

    public static List<UsuarioResumidoDTO> conveterParaListaDTO(List<Usuario> listaUsuario) {
        List<UsuarioResumidoDTO> listaDTO = new ArrayList<>();
        listaUsuario.forEach(u -> listaDTO.add(UsuarioResumidoDTO.conveterParaDTO(u)));
        return listaDTO;
    }
}
