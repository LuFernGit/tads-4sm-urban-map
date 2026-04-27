package br.com.senac.urbanmap.services;

import br.com.senac.urbanmap.controllers.dtos.tag.TagCadastroDTO;
import br.com.senac.urbanmap.controllers.dtos.tag.TagDTO;
import br.com.senac.urbanmap.controllers.dtos.tag.TagPainelAdminDTO;
import br.com.senac.urbanmap.entities.tag.Tag;
import br.com.senac.urbanmap.repositories.TagRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TagService {
    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Set<TagDTO> buscarTag(String nome) {
        if (nome == null || nome.isBlank())
            return TagDTO.converterParaListaDTO(new HashSet<>(this.tagRepository.findAll()));

        return TagDTO.converterParaListaDTO(this.tagRepository.findByNomeContainingIgnoreCase(nome));
    }

    @Transactional
    public TagPainelAdminDTO obterPainel() {
        Long qtdTags = this.tagRepository.count();
        Long qtdCategorias = this.tagRepository.contarCategorias();
        Set<TagDTO> listaDTO = TagDTO.converterParaListaDTO(new HashSet<>(this.tagRepository.findAll()));
        return new TagPainelAdminDTO(qtdTags, qtdCategorias, listaDTO);
    }

    public TagDTO cadastrar(TagCadastroDTO dto) {
        Tag tag = TagCadastroDTO.converterParaTag(dto);
        tag = this.tagRepository.save(tag);
        return TagDTO.converterParaDTO(tag);
    }

    @Transactional
    public TagDTO alterar(Long idTag, TagDTO dto) {
        Tag tag = this.tagRepository.findById(idTag).orElseThrow();
        tag.setNome(dto.nome());
        tag.setCategoria(dto.categoria());
        tag.setCor(dto.cor());
        return TagDTO.converterParaDTO(this.tagRepository.save(tag));
    }


    public void excluir(Long id) {
        this.tagRepository.deleteById(id);
    }

    public boolean jaCadastrado(Long id) {
        return this.tagRepository.existsById(id);
    }

    public boolean nomeCadastrado(String nome) {
        return this.tagRepository.existsByNome(nome);
    }
}
