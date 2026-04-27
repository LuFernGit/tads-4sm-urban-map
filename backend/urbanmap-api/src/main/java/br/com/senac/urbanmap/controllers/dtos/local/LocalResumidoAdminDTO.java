package br.com.senac.urbanmap.controllers.dtos.local;

import br.com.senac.urbanmap.controllers.dtos.tag.TagDTO;
import br.com.senac.urbanmap.entities.local.Local;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

// exclusivo para a area de gerenciamento de local do admin
public record LocalResumidoAdminDTO(
        Long id,
        String nome,
        String descricao,
        List<String> fotosUrl,
        String endereco,
        String cidade,
        String estado,
        BigDecimal latitude,
        BigDecimal longitude,
        Set<TagDTO> tags,
        boolean status
) {
    public static LocalResumidoAdminDTO converterParaDTO(Local l) {
        return new LocalResumidoAdminDTO(
                l.getId(),
                l.getNome(),
                l.getDescricao(),
                l.getFotosUrl(),
                l.getEndereco(),
                l.getCidade(),
                l.getEstado(),
                l.getLatitude(),
                l.getLongitude(),
                TagDTO.converterParaListaDTO(l.getTags()),
                l.isStatus()
        );
    }

    public static List<LocalResumidoAdminDTO> converterParaListaDTO(List<Local> listaLocal) {
        List<LocalResumidoAdminDTO> listaDTO = new ArrayList<>();
        listaLocal.forEach(local -> listaDTO.add(LocalResumidoAdminDTO.converterParaDTO(local)));
        return listaDTO;
    }
}