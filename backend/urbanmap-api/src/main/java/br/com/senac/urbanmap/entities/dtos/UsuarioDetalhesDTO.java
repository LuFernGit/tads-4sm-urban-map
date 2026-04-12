package br.com.senac.urbanmap.entities.dtos;

import br.com.senac.urbanmap.entities.usuario.Funcao;
import br.com.senac.urbanmap.entities.usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

public record UsuarioDetalhesDTO(
        Long id,
        String nome,
        String nomeUsuario,
        String imagemUrl,
        String email,
        String telefone,
        Funcao funcao
) {
    public static UsuarioDetalhesDTO converterParaDTO(Usuario usuario) {
        return new UsuarioDetalhesDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getNomeUsuario(),
                usuario.getImagemUrl(),
                usuario.getEmail(),
                usuario.getTelefone(),
                usuario.getFuncao());
    }

    public static List<UsuarioDetalhesDTO> converterListaParaDTO(List<Usuario> usuarios) {
        List<UsuarioDetalhesDTO> listaDTO = new ArrayList<>();
        usuarios.forEach(usuario ->
                listaDTO.add(UsuarioDetalhesDTO.converterParaDTO(usuario))
        );
        return listaDTO;
    }
}