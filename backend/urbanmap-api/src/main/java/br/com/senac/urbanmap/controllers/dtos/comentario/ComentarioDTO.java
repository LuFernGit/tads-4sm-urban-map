package br.com.senac.urbanmap.controllers.dtos.comentario;

import br.com.senac.urbanmap.controllers.dtos.usuario.UsuarioResumidoDTO;
import br.com.senac.urbanmap.entities.comentario.Comentario;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public record ComentarioDTO(
        Long id,
        UsuarioResumidoDTO autor,
        Long localId,
        String conteudo,
        LocalDateTime dataCriacao
) {
    public static ComentarioDTO converterParaDTO(Comentario c) {
        return new ComentarioDTO(c.getId(), UsuarioResumidoDTO.conveterParaDTO(c.getAutor()), c.getLocal().getId(), c.getConteudo(), c.getDataCriacao());
    }

    public static List<ComentarioDTO> converterParaListaDTO(List<Comentario> listaComentario) {
        List<ComentarioDTO> listaDTO = new ArrayList<>();
        listaComentario.forEach(c -> listaDTO.add(ComentarioDTO.converterParaDTO(c)));
        return listaDTO;
    }
}
