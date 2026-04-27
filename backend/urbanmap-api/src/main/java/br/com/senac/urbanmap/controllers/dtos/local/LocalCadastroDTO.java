package br.com.senac.urbanmap.controllers.dtos.local;

import br.com.senac.urbanmap.entities.local.Local;
import br.com.senac.urbanmap.entities.tag.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public record LocalCadastroDTO(
        @NotBlank(message = "Nome é obrigatório") String nome,
        @NotBlank(message = "Descrição é obrigatório") String descricao,
        @NotBlank(message = "Endereço é obrigatório") String endereco,
        @NotBlank(message = "Cidade é obrigatório") String cidade,
        @NotBlank(message = "Estado é obrigatório") String estado,
        @NotBlank(message = "Cep é obrigatório") String cep,
        @NotNull(message = "Latitude é obrigatório") BigDecimal latitude,
        @NotNull(message = "Longitude é obrigatório") BigDecimal longitude,
        List<String> urls,
        Set<Long> tagsId
) {
    public static Local converterParaLocal(LocalCadastroDTO dto, Set<Tag> tags, List<String> fotosUrl) {
        return Local.builder()
                .nome(dto.nome())
                .descricao(dto.descricao())
                .fotosUrl(fotosUrl)
                .endereco(dto.endereco())
                .cidade(dto.cidade())
                .estado(dto.estado())
                .cep(dto.cep())
                .latitude(dto.latitude())
                .longitude(dto.longitude())
                .qtdLike(0L)
                .qtdSalvo(0L)
                .tags(tags)
                .build();
    }
}