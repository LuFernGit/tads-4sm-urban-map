package br.com.senac.urbanmap.controllers.dtos.local;

import br.com.senac.urbanmap.controllers.dtos.tag.TagDTO;
import br.com.senac.urbanmap.entities.local.Local;
import br.com.senac.urbanmap.entities.tag.Tag;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public record LocalCompletoDTO(
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
        Set<TagDTO> tags
) {
    public static LocalCompletoDTO converterParaDto(Local local) {
        return new LocalCompletoDTO(
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
                TagDTO.converterParaListaDTO((local.getTags()))
        );
    }

    public static Local converterParaLocal(LocalCompletoDTO dto) {
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
                .tags(TagDTO.converterParaListaTag(dto.tags()))
                .build();
    }
}
