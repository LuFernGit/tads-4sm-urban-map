package br.com.senac.urbanmap.controllers.dtos;


import br.com.senac.urbanmap.entities.tag.Tag;
import jakarta.validation.constraints.NotBlank;

public record TagCadastroDTO(
        @NotBlank(message = "o campo 'nome' é obrigatório") String nome,
        @NotBlank(message = "o campo 'categoria' é obrigatório") String categoria,
        @NotBlank(message = "o campo 'cor' é obrigatorio") String cor
) {
    public static Tag converterParaTag(TagCadastroDTO dto) {
        return Tag.builder()
                .nome(dto.nome())
                .categoria(dto.categoria())
                .cor(dto.cor())
                .build();
    }
}
