package br.com.senac.urbanmap.controllers.dtos;

import br.com.senac.urbanmap.entities.local.Local;
import br.com.senac.urbanmap.entities.tag.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public record LocalCadastroDTO(
        @NotBlank(message = "o campo 'nome' é obrigatório") String nome,
        @NotBlank(message = "o campo 'descrição' é obrigatoria") String descricao,
        @NotBlank(message = "o campo 'endereço' é obrigatório") String endereco,
        @NotBlank(message = "o campo 'cidade' é obrigatório") String cidade,
        @NotBlank(message = "o campo 'estado' é obrigatório") String estado,
        @NotBlank(message = "o campo 'cep' é obrigatório") String cep,
        @NotNull(message = "o campo 'latitude' é obrigatório") BigDecimal latitude,
        @NotNull(message = "o campo 'longitude' é obrigatorio") BigDecimal longitude,
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
                .build();
    }
}