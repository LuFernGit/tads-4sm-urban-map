package br.com.senac.urbanmap.entities.dtos;

// resposta padrão para login e cadastro de usuário
public record UsuarioAutenticadoDTO(
        String token,
        UsuarioDetalhesDTO usuario) {
}