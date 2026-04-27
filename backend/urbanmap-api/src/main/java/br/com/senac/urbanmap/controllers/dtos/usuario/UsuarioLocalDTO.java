package br.com.senac.urbanmap.controllers.dtos.usuario;

import br.com.senac.urbanmap.entities.usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

public record UsuarioLocalDTO(
        String nome,
        String fotoUrl
) {
    public static UsuarioLocalDTO converterParaDTO(Usuario u) {
        return new UsuarioLocalDTO(u.getNome(), u.getFotoUrl());
    }

    public static List<UsuarioLocalDTO> converterParaListaDTO(List<Usuario> listaUsuario) {
        List<UsuarioLocalDTO> listaDTO = new ArrayList<>();
        listaUsuario.forEach(u -> listaDTO.add(UsuarioLocalDTO.converterParaDTO(u)));
        return listaDTO;
    }

}
