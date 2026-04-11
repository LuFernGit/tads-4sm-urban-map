package br.com.senac.urbanmap.services;

import br.com.senac.urbanmap.entities.dtos.TagCadastroDTO;
import br.com.senac.urbanmap.entities.tag.Tag;
import br.com.senac.urbanmap.repositories.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<Tag> buscar() {
        return this.tagRepository.findAll();
    }

    public Tag cadastrar(TagCadastroDTO dto) {
        Tag tag = TagCadastroDTO.converterParaTag(dto);
        tag = this.tagRepository.save(tag);
        return tag;
    }

    public Tag alterar(Tag tag) {
        tag = this.tagRepository.save(tag);
        return tag;
    }

    public void excluir(Long id) {
        this.tagRepository.deleteById(id);
    }

    public boolean jaCadastrado(Long id) {
        return this.tagRepository.existsById(id);
    }

    public boolean nomeCadastrado(String nome){
        return this.tagRepository.existsByNome(nome);
    }


}
