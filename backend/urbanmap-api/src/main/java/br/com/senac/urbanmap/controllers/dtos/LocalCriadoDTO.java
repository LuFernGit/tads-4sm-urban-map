package br.com.senac.urbanmap.controllers.dtos;

import br.com.senac.urbanmap.entities.local.Local;
import br.com.senac.urbanmap.entities.tag.Tag;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public record LocalCriadoDTO(
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
    public static LocalCriadoDTO converterParaDto(Local local) {
        return new LocalCriadoDTO(
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
}
