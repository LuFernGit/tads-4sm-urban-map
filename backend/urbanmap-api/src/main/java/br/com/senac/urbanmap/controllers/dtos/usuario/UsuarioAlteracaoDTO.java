package br.com.senac.urbanmap.controllers.dtos.usuario;

public record UsuarioAlteracaoDTO(
        Long id,
        String nome,
        String nomeUsuario,
        String telefone
) {

}
