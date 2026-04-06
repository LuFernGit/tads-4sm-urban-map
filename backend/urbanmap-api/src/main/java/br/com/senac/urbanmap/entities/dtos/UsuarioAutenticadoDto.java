package br.com.senac.urbanmap.entities.dtos;

public record UsuarioAutenticadoDto(
        String token,
        UsuarioDetalhesDto usuarioDetalhes) {
}