package br.com.senac.urbanmap.controllers;

import br.com.senac.urbanmap.controllers.dtos.LocalAlteracaoDTO;
import br.com.senac.urbanmap.controllers.dtos.LocalCadastroDTO;
import br.com.senac.urbanmap.controllers.dtos.LocalPadraoDTO;
import br.com.senac.urbanmap.controllers.dtos.LocalListaReduzidaDTO;
import br.com.senac.urbanmap.entities.local.Local;
import br.com.senac.urbanmap.entities.tag.Tag;
import br.com.senac.urbanmap.services.LocalService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/locais")
@CrossOrigin(origins = "*")
public class LocalController {

    private final LocalService localService;

    public LocalController(LocalService localService) {
        this.localService = localService;
    }

    @GetMapping("/todos")
    public ResponseEntity<List<LocalListaReduzidaDTO>> buscarTodos() {
        return ResponseEntity.ok(LocalListaReduzidaDTO.converterParaListaDTO(this.localService.buscarTodos()));
    }

    // nome ou tags
    @GetMapping("/buscar/{nome}")
    public ResponseEntity<List<LocalListaReduzidaDTO>> buscarPorParametro(@PathVariable("nome") String nome, @RequestBody Set<Tag> tags) {
        return null;
    }

    @GetMapping("/buscar/{idlocal}")
    public ResponseEntity<LocalPadraoDTO> buscarLocal(@PathVariable("idLocal") Long idLocal) {
        return ResponseEntity.ok(LocalPadraoDTO.converterParaDto(this.localService.buscarLocal(idLocal)));
    }

    @PostMapping(value = "/cadastrar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<LocalPadraoDTO> cadastrar(
            @Parameter(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
            @RequestPart("local") @Valid LocalCadastroDTO dto,
            @RequestPart(value = "arquivos", required = false) List<MultipartFile> arquivos) {
        Local local = this.localService.cadastrar(dto, arquivos);
        return ResponseEntity.created(URI.create("/locais/" + local.getId())).body(LocalPadraoDTO.converterParaDto(local));
    }

    @PutMapping(value = "/alterar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> alterar(
            @Parameter(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
            @RequestPart("local") @Valid LocalAlteracaoDTO dto,
            @RequestPart(value = "arquivos", required = false) List<MultipartFile> arquivos) {
        this.localService.alterar(dto, arquivos);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deletar/{idLocal}")
    public ResponseEntity<?> deletarLocal(@PathVariable("idLocal") Long idLocal) {
        this.localService.remover(idLocal);
        return ResponseEntity.noContent().build();
    }

}