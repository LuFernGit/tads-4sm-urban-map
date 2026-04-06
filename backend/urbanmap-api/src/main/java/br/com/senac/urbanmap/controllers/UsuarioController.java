package br.com.senac.urbanmap.controllers;

import br.com.senac.urbanmap.entities.dtos.UsuarioAutenticadoDto;
import br.com.senac.urbanmap.entities.dtos.UsuarioDetalhesDto;
import br.com.senac.urbanmap.entities.dtos.UsuarioLoginDto;
import br.com.senac.urbanmap.entities.dtos.UsuarioNovoDto;
import br.com.senac.urbanmap.entities.usuario.Usuario;
import br.com.senac.urbanmap.security.TokenService;
import br.com.senac.urbanmap.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService service;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public UsuarioController(UsuarioService service, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.service = service;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<UsuarioAutenticadoDto> cadastro(@Valid @RequestBody UsuarioNovoDto dto) {
        Usuario usuario = service.registrarNovoUsuario(dto);
        String token = tokenService.geradorDeToken(usuario);
        UsuarioDetalhesDto detalhes = new UsuarioDetalhesDto(usuario.getId(), usuario.getNome(), usuario.getNomeUsuario(),
                usuario.getEmail(), usuario.getTelefone(), usuario.getFuncao());
        UsuarioAutenticadoDto usuarioAutenticado = new UsuarioAutenticadoDto(token, detalhes);
        return ResponseEntity.created(URI.create("/usuario/" + usuarioAutenticado.usuarioDetalhes().id())).body(usuarioAutenticado);
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioAutenticadoDto> login(@RequestBody @Valid UsuarioLoginDto dto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        Usuario usuario = (Usuario) auth.getPrincipal();
        String token = tokenService.geradorDeToken(usuario);
        UsuarioDetalhesDto detalhes = new UsuarioDetalhesDto(usuario.getId(), usuario.getNome(),
                usuario.getNomeUsuario(), usuario.getEmail(), usuario.getTelefone(), usuario.getFuncao());
        return ResponseEntity.ok(new UsuarioAutenticadoDto(token, detalhes));
    }

}