package br.com.senac.urbanmap.controllers.dtos;

import br.com.senac.urbanmap.entities.local.Local;
import br.com.senac.urbanmap.entities.tag.Tag;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public record LocalPadraoDTO(
        Long id,
        String nome,
        String descricao,
        List<String> fotosUrl,
        String endereco,
        String cidade,
        String estado,
        String cep,
        BigDecimal latitude,
        BigDecimal longitude,
        Long qtdLike,
        Long qtdSalvo,
        Set<Tag> tags
) {
    public static LocalPadraoDTO converterParaDto(Local local) {
        return new LocalPadraoDTO(
                local.getId(),
                local.getNome(),
                local.getDescricao(),
                local.getFotosUrl(),
                local.getEndereco(),
                local.getCidade(),
                local.getEstado(),
                local.getCep(),
                local.getLatitude(),
                local.getLongitude(),
                local.getQtdLike(),
                local.getQtdSalvo(),
                local.getTags()
        );
    }

    public static Local converterParaLocal(LocalPadraoDTO dto) {
        return Local.builder()
                .id(dto.id())
                .nome(dto.nome())
                .descricao(dto.descricao())
                .fotosUrl(dto.fotosUrl())
                .endereco(dto.endereco())
                .cidade(dto.cidade())
                .estado(dto.estado())
                .cep(dto.cep())
                .latitude(dto.latitude())
                .longitude(dto.longitude())
                .qtdLike(dto.qtdLike())
                .qtdSalvo(dto.qtdSalvo())
                .tags(dto.tags())
                .build();
    }
}
