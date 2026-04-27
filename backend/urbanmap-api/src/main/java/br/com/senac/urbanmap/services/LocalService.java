package br.com.senac.urbanmap.services;

import br.com.senac.urbanmap.controllers.dtos.*;
import br.com.senac.urbanmap.controllers.dtos.local.*;
import br.com.senac.urbanmap.controllers.dtos.tag.TagDTO;
import br.com.senac.urbanmap.entities.local.Local;
import br.com.senac.urbanmap.entities.tag.Tag;
import br.com.senac.urbanmap.entities.usuario.Usuario;
import br.com.senac.urbanmap.exception.ErroLocalServiceException;
import br.com.senac.urbanmap.repositories.LocalRepository;
import br.com.senac.urbanmap.repositories.TagRepository;
import br.com.senac.urbanmap.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class LocalService {

    private final UsuarioRepository usuarioRepository;
    private final LocalRepository localRepository;
    private final TagRepository tagRepository;
    private final ImagemService imagemService;

    public LocalService(UsuarioRepository usuarioRepository, LocalRepository localRepository, TagRepository tagRepository, ImagemService imagemService) {
        this.usuarioRepository = usuarioRepository;
        this.localRepository = localRepository;
        this.tagRepository = tagRepository;
        this.imagemService = imagemService;
    }

    public List<Local> buscarTodos() {
        return this.localRepository.findAll();
    }

    public List<LocalResumidoUsuarioDTO> obterLocaisSalvos(Long idUsuario) {
        Optional<Usuario> opt = this.usuarioRepository.findById(idUsuario);
        if (opt.isEmpty()) throw new ErroLocalServiceException("Usuario não encontrado");
        List<LocalResumidoUsuarioDTO> listaDTO = new ArrayList<>();
        Usuario usuario = opt.get();
        for (Local local : usuario.getSalvos()) {
            List<Usuario> curtidas = this.usuarioRepository.findByLikesId(local.getId());
            listaDTO.add(LocalResumidoUsuarioDTO.converterParaDTO(local, curtidas));
        }
        return listaDTO;
    }

    public List<LocalResumidoUsuarioDTO> obterLocaisCurtidos(Long idUsuario) {
        Optional<Usuario> opt = this.usuarioRepository.findById(idUsuario);
        if (opt.isEmpty()) throw new ErroLocalServiceException("Usuario não encontrado");
        List<LocalResumidoUsuarioDTO> listaDTO = new ArrayList<>();
        Usuario usuario = opt.get();
        for (Local local : usuario.getLikes()) {
            List<Usuario> curtidas = this.usuarioRepository.findByLikesId(local.getId());
            listaDTO.add(LocalResumidoUsuarioDTO.converterParaDTO(local, curtidas));
        }
        return listaDTO;
    }

    public List<LocalResumidoUsuarioDTO> buscarPorParametrosUsuario(String nome, Set<Long> idTags) {
        idTags = idTags == null ? new HashSet<>() : idTags;
        List<Local> locais;
        if ((nome == null || nome.isBlank()) && idTags.isEmpty()) {
            locais = this.localRepository.findAll();
        } else {
            Set<Tag> tags = new HashSet<>(this.tagRepository.findAllById(idTags));
            locais = this.localRepository.findDistinctByNomeContainingIgnoreCaseOrTagsIn(nome, tags);
        }
        List<LocalResumidoUsuarioDTO> listaDTO = new ArrayList<>();
        for (Local local : locais) {
            List<Usuario> curtidas = this.usuarioRepository.findByLikesId(local.getId());
            listaDTO.add(LocalResumidoUsuarioDTO.converterParaDTO(local, curtidas));
        }
        return listaDTO;
    }

    public Local buscarLocal(Long idLocal) {
        Optional<Local> opt = this.localRepository.findById(idLocal);
        if (opt.isEmpty()) throw new ErroLocalServiceException("Local não encontrado");
        return opt.get();
    }

    public Local cadastrar(LocalCadastroDTO dto, List<MultipartFile> arquivos) {
        List<String> fotos;
        if (dto.urls().isEmpty() && (arquivos != null && !arquivos.isEmpty())) {
            fotos = new ArrayList<>();
            arquivos.forEach(arquivo ->
                    fotos.add(this.imagemService.salvarImagem(arquivo, "locais"))
            );
        } else {
            fotos = new ArrayList<>(dto.urls());
        }
        Set<Tag> tags = new HashSet<>(this.tagRepository.findAllById(dto.tagsId()));
        return this.localRepository.save(LocalCadastroDTO.converterParaLocal(dto, tags, fotos));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void alterar(LocalAlteracaoDTO dto, List<MultipartFile> arquivos) {
        Optional<Local> opt = this.localRepository.findById(dto.id());
        if (opt.isEmpty()) throw new ErroLocalServiceException("Local não encontrado");
        Local l = opt.get();
        l.setNome(dto.nome());
        l.setDescricao(dto.descricao());
        l.setEndereco(dto.endereco());
        l.setCidade(dto.cidade());
        l.setEstado(dto.estado());
        l.setCep(dto.cep());
        l.setLatitude(dto.latitude());
        l.setLongitude(dto.longitude());

        if (!dto.urls().isEmpty() || (arquivos != null && !arquivos.isEmpty())) {
            l.getFotosUrl().forEach(foto -> this.imagemService.excluirImagem(foto));
        }

        if (!dto.urls().isEmpty()) {
            l.setFotosUrl(dto.urls());
        } else if (arquivos != null && !arquivos.isEmpty()) {
            List<String> fotos = new ArrayList<>();
            arquivos.forEach(arquivo ->
                    fotos.add(this.imagemService.salvarImagem(arquivo, "locais"))
            );
            l.setFotosUrl(fotos);
        }

        Set<Tag> tags = new HashSet<>(this.tagRepository.findAllById(dto.tagsId()));
        l.setTags(tags);
        this.localRepository.save(l);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void remover(Long id) {
        Optional<Local> opt = this.localRepository.findById(id);
        if (opt.isEmpty()) throw new ErroLocalServiceException("Local não encontrado");
        Local l = opt.get();
        l.getFotosUrl().forEach(foto -> this.imagemService.excluirImagem(foto));
        this.localRepository.delete(l);
    }

    public EstatisticaDTO estatisticaGeral() {
        Long qtdLocais = this.localRepository.count();
        Long qtdUsuarios = this.usuarioRepository.count();
        Long qtdCurtidas = this.localRepository.somarLikes();
        Long qtdSalvos = this.localRepository.somarSalvos();
        return new EstatisticaDTO(qtdLocais, qtdUsuarios, qtdCurtidas, qtdSalvos);
    }

    public LocalPainelUsuarioDTO obterPainelUsuario(Long idUsuario) {
        Optional<Usuario> optUsuario = this.usuarioRepository.findById(idUsuario);
        if (optUsuario.isEmpty()) {
            throw new ErroLocalServiceException("Usuário não encontrado");
        }
        Usuario usuarioLogado = optUsuario.get();

        List<Local> todosOsLocais = this.localRepository.findAll();
        List<LocalResumidoUsuarioDTO> listaLocalDTO = new ArrayList<>();

        for (Local local : todosOsLocais) {
            List<Usuario> quemCurtiu = this.usuarioRepository.findByLikesId(local.getId());
            listaLocalDTO.add(LocalResumidoUsuarioDTO.converterParaDTO(local, quemCurtiu));
        }

        Set<TagDTO> tagsDTO = TagDTO.converterParaListaDTO(new HashSet<>(this.tagRepository.findAll()));

        List<Long> idsCurtidos = new ArrayList<>();

        for (Local l : usuarioLogado.getLikes()) {
            idsCurtidos.add(l.getId());
        }

        List<Long> idsSalvos = new ArrayList<>();
        for (Local l : usuarioLogado.getSalvos()) {
            idsSalvos.add(l.getId());
        }

        return new LocalPainelUsuarioDTO(listaLocalDTO, tagsDTO, idsCurtidos, idsSalvos);
    }

    public LocalPainelAdminDTO obterPainelAdmin() {
        Long qtdLocais = this.localRepository.count();
        List<LocalResumidoAdminDTO> locaisDTO = LocalResumidoAdminDTO.converterParaListaDTO(this.localRepository.findAll());
        Set<TagDTO> tagsDTO = TagDTO.converterParaListaDTO(new HashSet<>(this.tagRepository.findAll()));
        return new LocalPainelAdminDTO(qtdLocais, locaisDTO, tagsDTO);
    }

    // Auxiliares
    public boolean localExiste(Long id) {
        return this.localRepository.existsById(id);
    }

}
