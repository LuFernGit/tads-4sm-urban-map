package br.com.senac.urbanmap.controllers.dtos.local;

import br.com.senac.urbanmap.controllers.dtos.tag.TagDTO;
import br.com.senac.urbanmap.controllers.dtos.usuario.UsuarioLocalDTO;
import br.com.senac.urbanmap.entities.local.Local;
import br.com.senac.urbanmap.entities.usuario.Usuario;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public record LocalResumidoUsuarioDTO(
        Long id,
        String nome,
        String descricao,
        List<String> fotosUrl,
        String endereco,
        BigDecimal latitude,
        BigDecimal longitude,
        List<UsuarioLocalDTO> usuarios,
        Set<TagDTO> tags,
        Long qtdLike
) {
    public static LocalResumidoUsuarioDTO converterParaDTO(Local l, List<Usuario> listaUsuarios) {
        return new LocalResumidoUsuarioDTO(
                l.getId(),
                l.getNome(),
                l.getDescricao(),
                l.getFotosUrl(),
                l.getEndereco(),
                l.getLatitude(),
                l.getLongitude(),
                UsuarioLocalDTO.converterParaListaDTO(listaUsuarios),
                TagDTO.converterParaListaDTO(l.getTags()),
                l.getQtdLike()
        );
    }
}
