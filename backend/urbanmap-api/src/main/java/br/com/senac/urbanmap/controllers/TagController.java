package br.com.senac.urbanmap.controllers;

import br.com.senac.urbanmap.entities.dtos.TagCadastroDTO;
import br.com.senac.urbanmap.entities.dtos.TagTodasDTO;
import br.com.senac.urbanmap.entities.tag.Tag;
import br.com.senac.urbanmap.services.TagService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("tag")
@CrossOrigin("*")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping()
    public ResponseEntity<TagTodasDTO> buscar() {
        return ResponseEntity.ok(new TagTodasDTO(this.tagService.buscar()));
    }

    @PostMapping()
    public ResponseEntity<Tag> cadastrar(@RequestBody @Valid TagCadastroDTO dto) {
        if (this.tagService.nomeCadastrado(dto.nome()))
            return ResponseEntity.badRequest().build();

        Tag tag = this.tagService.cadastrar(dto);
        return ResponseEntity.created(URI.create("/tag/" + tag.getId())).body(tag);
    }

    @PutMapping()
    public ResponseEntity<Tag> alterar(@RequestBody Tag tag) {
        if (!this.tagService.jaCadastrado(tag.getId()))
            return ResponseEntity.notFound().build();

        tag = this.tagService.alterar(tag);
        return ResponseEntity.ok(tag);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!this.tagService.jaCadastrado(id))
            return ResponseEntity.notFound().build();

        this.tagService.excluir(id);
        return ResponseEntity.noContent().build();
    }

}