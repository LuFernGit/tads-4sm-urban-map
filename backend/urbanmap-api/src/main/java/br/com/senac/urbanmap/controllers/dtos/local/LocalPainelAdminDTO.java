package br.com.senac.urbanmap.controllers.dtos.local;

import br.com.senac.urbanmap.controllers.dtos.tag.TagDTO;

import java.util.List;
import java.util.Set;

public record LocalPainelAdminDTO(
        Long qtdLocais,
        List<LocalResumidoAdminDTO> locais,
        Set<TagDTO> categorias
) {

}
