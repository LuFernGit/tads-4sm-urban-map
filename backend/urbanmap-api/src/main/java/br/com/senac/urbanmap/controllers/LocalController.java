package br.com.senac.urbanmap.controllers;

import br.com.senac.urbanmap.controllers.dtos.*;
import br.com.senac.urbanmap.controllers.dtos.local.*;
import br.com.senac.urbanmap.entities.local.Local;
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

    // USUARIO COMUM

    @GetMapping("/lista")
    public ResponseEntity<?> buscarTodos(@RequestParam("usuarioID") Long usuarioID) {
        return ResponseEntity.ok(this.localService.obterPainelUsuario(usuarioID));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<LocalResumidoUsuarioDTO>> buscarPorParametro(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "idTags", required = false) Set<Long> idTags) {

        List<LocalResumidoUsuarioDTO> locais = this.localService.buscarPorParametrosUsuario(nome, idTags);
        return ResponseEntity.ok(locais);
    }

    @GetMapping("/usuario/{idUsuario}/curtidos")
    public ResponseEntity<List<LocalResumidoUsuarioDTO>> listarCurtidos(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(this.localService.obterLocaisCurtidos(idUsuario));
    }

    @GetMapping("/usuario/{idUsuario}/salvos")
    public ResponseEntity<List<LocalResumidoUsuarioDTO>> listarSalvos(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(this.localService.obterLocaisSalvos(idUsuario));
    }

    @GetMapping("/buscar/{idLocal}")
    public ResponseEntity<LocalCompletoDTO> buscarLocal(@PathVariable("idLocal") Long idLocal) {
        return ResponseEntity.ok(LocalCompletoDTO.converterParaDto(this.localService.buscarLocal(idLocal)));
    }

    // ADMIN

    @GetMapping("/admin/painel")
    public ResponseEntity<LocalPainelAdminDTO> obterPainel() {
        return ResponseEntity.ok(this.localService.obterPainelAdmin());
    }

    @GetMapping("/admin/estatistica/geral")
    public ResponseEntity<EstatisticaDTO> obterEstatisticaGeral() {
        return ResponseEntity.ok(this.localService.estatisticaGeral());
    }

    @PostMapping(value = "/admin/cadastrar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<LocalCompletoDTO> cadastrar(
            @Parameter(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
            @RequestPart("local") @Valid LocalCadastroDTO dto,
            @RequestPart(value = "arquivos", required = false) List<MultipartFile> arquivos) {
        Local local = this.localService.cadastrar(dto, arquivos);
        return ResponseEntity.created(URI.create("/locais/" + local.getId())).body(LocalCompletoDTO.converterParaDto(local));
    }

    @PutMapping(value = "/admin/alterar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> alterar(
            @Parameter(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
            @RequestPart("local") @Valid LocalAlteracaoDTO dto,
            @RequestPart(value = "arquivos", required = false) List<MultipartFile> arquivos) {
        if (!this.localService.localExiste(dto.id()))
            return ResponseEntity.notFound().build();

        this.localService.alterar(dto, arquivos);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/admin/deletar/{idLocal}")
    public ResponseEntity<?> deletarLocal(@PathVariable("idLocal") Long idLocal) {
        if (!this.localService.localExiste(idLocal))
            return ResponseEntity.notFound().build();

        this.localService.remover(idLocal);
        return ResponseEntity.noContent().build();
    }
}