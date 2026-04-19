package br.com.senac.urbanmap.controllers.dtos;

import br.com.senac.urbanmap.entities.tag.Tag;

import java.util.ArrayList;
import java.util.List;

public record TagRespostaDTO(
        Long id,
        String nome,
        String categoria,
        String cor
) {
    public static TagRespostaDTO converterParaDTO(Tag tag) {
        return new TagRespostaDTO(
                tag.getId(),
                tag.getNome(),
                tag.getCategoria(),
                tag.getCor()
        );
    }

    public static List<TagRespostaDTO> converterParaListDTO(List<Tag> tags) {
        List<TagRespostaDTO> listaDTO = new ArrayList<>();
        tags.forEach(tag -> listaDTO.add(TagRespostaDTO.converterParaDTO(tag)));
        return listaDTO;
    }
}
