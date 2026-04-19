package br.com.senac.urbanmap.controllers.dtos;

import br.com.senac.urbanmap.entities.usuario.Funcao;
import br.com.senac.urbanmap.entities.usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

public record UsuarioRespostaDTO(
        Long id,
        String nome,
        String nomeUsuario,
        String imagemUrl,
        String email,
        String telefone,
        String funcao,
        String token
) {
    public static UsuarioRespostaDTO converterParaDTO(Usuario usuario, String token) {
        return new UsuarioRespostaDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getNomeUsuario(),
                usuario.getFotoUrl(),
                usuario.getEmail(),
                usuario.getTelefone(),
                usuario.getFuncao().getTipo(),
                token);
    }

    // por enquanto deixa comentado
    /*
    public static List<UsuarioRespostaDTO> converterListaParaDTO(List<Usuario> usuarios) {
        List<UsuarioRespostaDTO> listaDTO = new ArrayList<>();
        usuarios.forEach(usuario ->
                listaDTO.add(UsuarioRespostaDTO.converterParaDTO(usuario))
        );
        return listaDTO;
    }
    */
}