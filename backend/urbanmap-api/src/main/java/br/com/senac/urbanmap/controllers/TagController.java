package br.com.senac.urbanmap.controllers;

import br.com.senac.urbanmap.controllers.dtos.TagCadastroDTO;
import br.com.senac.urbanmap.controllers.dtos.TagRespostaDTO;
import br.com.senac.urbanmap.entities.tag.Tag;
import br.com.senac.urbanmap.services.TagService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tag")
@CrossOrigin(origins = "*")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/todas")
    public ResponseEntity<List<TagRespostaDTO>> buscar() {
        return ResponseEntity.ok(TagRespostaDTO.converterParaListDTO(this.tagService.buscar()));
    }

    @PostMapping()
    public ResponseEntity<TagRespostaDTO> cadastrar(@RequestBody @Valid TagCadastroDTO dto) {
        if (this.tagService.nomeCadastrado(dto.nome()))
            return ResponseEntity.badRequest().build();
        TagRespostaDTO respostaDTO = TagRespostaDTO.converterParaDTO(this.tagService.cadastrar(dto));
        return ResponseEntity.created(URI.create("/tag/" + respostaDTO.id())).body(respostaDTO);
    }

    @PutMapping()
    public ResponseEntity<TagRespostaDTO> alterar(@RequestBody Tag tag) {
        if (!this.tagService.jaCadastrado(tag.getId()))
            return ResponseEntity.notFound().build();
        TagRespostaDTO respostaDTO = TagRespostaDTO.converterParaDTO(this.tagService.alterar(tag));
        return ResponseEntity.ok(respostaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!this.tagService.jaCadastrado(id))
            return ResponseEntity.notFound().build();
        this.tagService.excluir(id);
        return ResponseEntity.noContent().build();
    }

}