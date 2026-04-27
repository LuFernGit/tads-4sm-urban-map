package br.com.senac.urbanmap.controllers.dtos.tag;

import java.util.Set;

public record TagPainelAdminDTO(
        Long qtdTags,
        Long qtdCategorias,
        Set<TagDTO> tags
) {

}
