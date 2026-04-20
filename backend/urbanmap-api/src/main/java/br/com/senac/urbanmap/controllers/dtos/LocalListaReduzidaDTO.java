package br.com.senac.urbanmap.controllers.dtos;

import br.com.senac.urbanmap.entities.local.Local;
import br.com.senac.urbanmap.entities.tag.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public record LocalListaReduzidaDTO(
        Long id,
        String nome,
        List<String> fotosUrl,
        Set<Tag> tags,
        Long qtdLike
) {
    private static LocalListaReduzidaDTO converterParaListaReduzidaDTO(Local local) {
        return new LocalListaReduzidaDTO(
                local.getId(),
                local.getNome(),
                local.getFotosUrl(),
                local.getTags(),
                local.getQtdLike()
        );
    }

    public static List<LocalListaReduzidaDTO> converterParaListaDTO(List<Local> locais) {
        List<LocalListaReduzidaDTO> listaDTO = new ArrayList<>();
        locais.forEach(local -> listaDTO.add(LocalListaReduzidaDTO.converterParaListaReduzidaDTO(local)));
        return listaDTO;
    }
}
