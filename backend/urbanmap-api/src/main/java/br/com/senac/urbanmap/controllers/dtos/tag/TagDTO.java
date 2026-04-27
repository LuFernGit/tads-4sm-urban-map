package br.com.senac.urbanmap.controllers.dtos.tag;

import br.com.senac.urbanmap.entities.tag.Tag;

import java.util.HashSet;
import java.util.Set;

public record TagDTO(
        Long id,
        String nome,
        String categoria,
        String cor
) {
    public static Tag converterParaTag(TagDTO dto) {
        return new Tag(dto.id(), dto.nome(), dto.categoria(), dto.cor());
    }

    public static Set<Tag> converterParaListaTag(Set<TagDTO> dtoLista) {
        Set<Tag> tags = new HashSet<>();
        dtoLista.forEach(dto -> tags.add(TagDTO.converterParaTag(dto)));
        return tags;
    }


    public static TagDTO converterParaDTO(Tag tag) {
        return new TagDTO(
                tag.getId(),
                tag.getNome(),
                tag.getCategoria(),
                tag.getCor()
        );
    }

    public static Set<TagDTO> converterParaListaDTO(Set<Tag> tags) {
        Set<TagDTO> listaDTO = new HashSet<>();
        tags.forEach(tag -> listaDTO.add(TagDTO.converterParaDTO(tag)));
        return listaDTO;
    }
}
