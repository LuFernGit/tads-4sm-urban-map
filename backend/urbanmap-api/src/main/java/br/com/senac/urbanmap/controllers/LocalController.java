package br.com.senac.urbanmap.controllers;

import br.com.senac.urbanmap.controllers.dtos.LocalCadastroDTO;
import br.com.senac.urbanmap.controllers.dtos.LocalCriadoDTO;
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

@RestController
@RequestMapping("/locais")
@CrossOrigin(origins = "*")
public class LocalController {

    private final LocalService localService;

    public LocalController(LocalService localService) {
        this.localService = localService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<LocalCriadoDTO> cadastrar(
            @Parameter(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
            @RequestPart("local") @Valid LocalCadastroDTO dto,
            @RequestPart(value = "arquivos", required = false) List<MultipartFile> arquivos) {
        Local local = this.localService.cadastrar(dto, arquivos);
        return ResponseEntity.created(URI.create("/locais/" + local.getId())).body(LocalCriadoDTO.converterParaDto(local));
    }
}