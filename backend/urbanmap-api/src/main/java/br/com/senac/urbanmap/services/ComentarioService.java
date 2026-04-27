package br.com.senac.urbanmap.services;

import br.com.senac.urbanmap.controllers.dtos.comentario.ComentarioCadastroDTO;
import br.com.senac.urbanmap.controllers.dtos.comentario.ComentarioDTO;
import br.com.senac.urbanmap.controllers.dtos.usuario.UsuarioResumidoDTO;
import br.com.senac.urbanmap.entities.comentario.Comentario;
import br.com.senac.urbanmap.entities.local.Local;
import br.com.senac.urbanmap.entities.usuario.Usuario;
import br.com.senac.urbanmap.repositories.ComentarioRepository;
import br.com.senac.urbanmap.repositories.LocalRepository;
import br.com.senac.urbanmap.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ComentarioService {
    private final ComentarioRepository comentarioRepository;
    private final UsuarioRepository usuarioRepository;
    private final LocalRepository localRepository;

    public ComentarioService(ComentarioRepository comentarioRepository, UsuarioRepository usuarioRepository, LocalRepository localRepository) {
        this.comentarioRepository = comentarioRepository;
        this.usuarioRepository = usuarioRepository;
        this.localRepository = localRepository;
    }

    public List<ComentarioDTO> listar(Long idLocal) {
        List<Comentario> comentarios = this.comentarioRepository.findByLocalId(idLocal);
        return ComentarioDTO.converterParaListaDTO(comentarios);
    }

    @Transactional
    public ComentarioDTO cadastrar(ComentarioCadastroDTO dto) {
        Optional<Usuario> optUsuario = this.usuarioRepository.findById(dto.usuarioId());
        Optional<Local> optLocal = this.localRepository.findById(dto.localId());

        // deixar sem exceção por enquanto

        // if(this.comentarioRepository.existsByAutorIdAndLocalId(dto.usuarioId(), dto.localId()))
        // throw new

        // deixar sem exceção por enquanto
        Usuario u = optUsuario.get();
        Local l = optLocal.get();
        Comentario c = Comentario.builder()
                .autor(u)
                .local(l)
                .conteudo(dto.conteudo())
                .dataCriacao(LocalDateTime.now()).build();
        c = this.comentarioRepository.save(c);
        return ComentarioDTO.converterParaDTO(c);
    }


}
