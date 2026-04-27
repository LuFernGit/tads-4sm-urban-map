package br.com.senac.urbanmap.controllers.dtos.tag;


import br.com.senac.urbanmap.entities.tag.Tag;
import jakarta.validation.constraints.NotBlank;

public record TagCadastroDTO(
        @NotBlank(message = "Nome é obrigatório") String nome,
        @NotBlank(message = "Categoria é obrigatório") String categoria,
        @NotBlank(message = "Cor é obrigatorio") String cor
) {
    public static Tag converterParaTag(TagCadastroDTO dto) {
        return Tag.builder()
                .nome(dto.nome())
                .categoria(dto.categoria())
                .cor(dto.cor())
                .build();
    }
}
