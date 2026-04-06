package br.com.senac.urbanmap.entities.dtos;

import br.com.senac.urbanmap.entities.usuario.Funcao;

public record UsuarioDetalhesDto(
        Long id, String nome,
        String nomeUsuario,
        String email,
        String telefone,
        Funcao funcao) {
}