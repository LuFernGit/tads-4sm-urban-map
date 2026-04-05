package br.com.senac.urbanmap.dtos;

import br.com.senac.urbanmap.entitys.usuario.Funcao;

public record UsuarioDetalhesDto(
        Long id, String nome,
        String nomeUsuario,
        String email,
        String telefone,
        Funcao funcao) {
}