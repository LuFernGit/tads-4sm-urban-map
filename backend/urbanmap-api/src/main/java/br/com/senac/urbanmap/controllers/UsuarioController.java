package br.com.senac.urbanmap.controllers;

import br.com.senac.urbanmap.dtos.UsuarioDetalhesDto;
import br.com.senac.urbanmap.dtos.UsuarioNovoDto;
import br.com.senac.urbanmap.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<UsuarioDetalhesDto> cadastro(@Valid @RequestBody UsuarioNovoDto dto) {
        UsuarioDetalhesDto resposta = service.registrarUsuario(dto);
        return ResponseEntity.created(URI.create("/usuario/" + resposta.id())).body(resposta);
    }

}