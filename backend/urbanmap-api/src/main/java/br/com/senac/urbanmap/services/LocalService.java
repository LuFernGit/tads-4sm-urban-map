package br.com.senac.urbanmap.services;

import br.com.senac.urbanmap.controllers.dtos.LocalAlteracaoDTO;
import br.com.senac.urbanmap.controllers.dtos.LocalCadastroDTO;
import br.com.senac.urbanmap.controllers.dtos.LocalPadraoDTO;
import br.com.senac.urbanmap.entities.local.Local;
import br.com.senac.urbanmap.entities.tag.Tag;
import br.com.senac.urbanmap.exception.ErroLocalServiceException;
import br.com.senac.urbanmap.repositories.LocalRepository;
import br.com.senac.urbanmap.repositories.TagRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class LocalService {

    private final LocalRepository localRepository;
    private final TagRepository tagRepository;
    private final ImagemService imagemService;

    public LocalService(LocalRepository localRepository, TagRepository tagRepository, ImagemService imagemService) {
        this.localRepository = localRepository;
        this.tagRepository = tagRepository;
        this.imagemService = imagemService;
    }

    public List<Local> buscarTodos() {
        return this.localRepository.findAll();
    }

    public Local buscarLocal(Long idLocal) {
        Optional<Local> opt = this.localRepository.findById(idLocal);
        if (opt.isEmpty()) throw new ErroLocalServiceException("Local não encontrado");
        return opt.get();
    }

    public Local cadastrar(LocalCadastroDTO dto, List<MultipartFile> arquivos) {
        List<String> fotos;
        if (dto.urls().isEmpty() && arquivos != null) {
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
        if (!dto.urls().isEmpty()) {
            l.setFotosUrl(dto.urls());
        } else if (!arquivos.isEmpty()) {
            List<String> fotos = new ArrayList<>();
            arquivos.forEach(arquivo ->
                    fotos.add(this.imagemService.salvarImagem(arquivo, "locais"))
            );
            l.setFotosUrl(fotos);
        }
        Set<Tag> tags = new HashSet<>(this.tagRepository.findAllById(dto.tagsId()));
        l.setTags(tags);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void remover(Long id) {
        Optional<Local> opt = this.localRepository.findById(id);
        if (opt.isEmpty()) throw new ErroLocalServiceException("Local não encontrado");
        Local l = opt.get();
        l.getFotosUrl().forEach(foto -> this.imagemService.excluirImagem(foto));
        this.localRepository.delete(l);
    }

}
