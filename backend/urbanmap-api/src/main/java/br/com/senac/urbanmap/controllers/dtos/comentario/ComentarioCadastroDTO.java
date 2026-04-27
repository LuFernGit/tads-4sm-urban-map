package br.com.senac.urbanmap.controllers.dtos.comentario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ComentarioCadastroDTO(
        @NotNull(message = "O id do usuario é obrigatório") Long usuarioId,
        @NotNull(message = "O id do local é obrigatório") Long localId,
        @NotBlank(message = "O conteudo do comentário é obrigatório") String conteudo
) {
}
