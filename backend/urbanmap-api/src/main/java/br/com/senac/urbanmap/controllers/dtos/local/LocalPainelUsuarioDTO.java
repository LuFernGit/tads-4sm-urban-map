package br.com.senac.urbanmap.controllers.dtos.local;

import br.com.senac.urbanmap.controllers.dtos.tag.TagDTO;

import java.util.List;
import java.util.Set;

public record LocalPainelUsuarioDTO(
        List<LocalResumidoUsuarioDTO> locais,
        Set<TagDTO> tags,
        List<Long> locaisCurtidos,
        List<Long> locaisSalvos
) {

}
