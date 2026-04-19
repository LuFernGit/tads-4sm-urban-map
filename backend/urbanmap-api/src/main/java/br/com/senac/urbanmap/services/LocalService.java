package br.com.senac.urbanmap.services;

import br.com.senac.urbanmap.controllers.dtos.LocalCadastroDTO;
import br.com.senac.urbanmap.entities.local.Local;
import br.com.senac.urbanmap.entities.tag.Tag;
import br.com.senac.urbanmap.repositories.LocalRepository;
import br.com.senac.urbanmap.repositories.TagRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

}
