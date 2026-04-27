package br.com.senac.urbanmap.controllers;

import br.com.senac.urbanmap.controllers.dtos.tag.TagCadastroDTO;
import br.com.senac.urbanmap.controllers.dtos.tag.TagDTO;
import br.com.senac.urbanmap.controllers.dtos.tag.TagPainelAdminDTO;
import br.com.senac.urbanmap.entities.tag.Tag;
import br.com.senac.urbanmap.services.TagService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/tag")
@CrossOrigin(origins = "*")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/admin/painel")
    public ResponseEntity<TagPainelAdminDTO> buscar() {
        return ResponseEntity.ok(this.tagService.obterPainel());
    }

    @GetMapping("/admin/buscar")
    public ResponseEntity<Set<TagDTO>> buscarTag(@RequestParam("nome") String nome) {
        return ResponseEntity.ok(this.tagService.buscarTag(nome));
    }

    @PostMapping()
    public ResponseEntity<TagDTO> cadastrar(@RequestBody @Valid TagCadastroDTO dto) {
        if (this.tagService.nomeCadastrado(dto.nome()))
            return ResponseEntity.badRequest().build();

        TagDTO respostaDTO = this.tagService.cadastrar(dto);
        return ResponseEntity.created(URI.create("/tag/" + respostaDTO.id())).body(respostaDTO);
    }

    @PutMapping("/{idTag}")
    public ResponseEntity<TagDTO> alterar(@PathVariable("idTag") Long idTag, @RequestBody @Valid TagDTO dto) {
        if (!this.tagService.jaCadastrado(idTag))
            return ResponseEntity.notFound().build();

        TagDTO respostaDTO = this.tagService.alterar(idTag, dto);
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