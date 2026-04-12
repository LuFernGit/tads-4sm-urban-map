package br.com.senac.urbanmap.controllers;

import br.com.senac.urbanmap.entities.dtos.UsuarioAutenticadoDTO;
import br.com.senac.urbanmap.entities.dtos.UsuarioDetalhesDTO;
import br.com.senac.urbanmap.entities.dtos.UsuarioLoginDTO;
import br.com.senac.urbanmap.entities.dtos.UsuarioDadosCadastroDTO;
import br.com.senac.urbanmap.entities.usuario.Usuario;
import br.com.senac.urbanmap.services.TokenService;
import br.com.senac.urbanmap.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping()
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public UsuarioController(UsuarioService service, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.usuarioService = service;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<UsuarioDetalhesDTO>> buscaTodos() {
        return ResponseEntity.ok(this.usuarioService.buscarTodos());
    }


    @PostMapping("/cadastro")
    public ResponseEntity<UsuarioAutenticadoDTO> cadastro(@Valid @RequestBody UsuarioDadosCadastroDTO dto) {
        Usuario usuario = usuarioService.cadastrar(dto);
        UsuarioDetalhesDTO usuarioDetalhesDTO = UsuarioDetalhesDTO.converterParaDTO(usuario);
        UsuarioAutenticadoDTO usuarioAutenticado = new UsuarioAutenticadoDTO(tokenService.geradorDeToken(usuario), usuarioDetalhesDTO);
        return ResponseEntity.created(URI.create("/usuario/" + usuarioAutenticado.usuario().id())).body(usuarioAutenticado);
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioAutenticadoDTO> login(@RequestBody @Valid UsuarioLoginDTO dto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        Usuario usuario = (Usuario) auth.getPrincipal();
        UsuarioDetalhesDTO usuarioDetalhesDTO = UsuarioDetalhesDTO.converterParaDTO(usuario);
        return ResponseEntity.ok(new UsuarioAutenticadoDTO(tokenService.geradorDeToken(usuario), usuarioDetalhesDTO));
    }

    @PutMapping("/usuario/{id}/foto") // O ID vai na URL
    public ResponseEntity<UsuarioDetalhesDTO> atualizarFoto(@PathVariable Long id, @RequestParam("foto") MultipartFile foto) {
        Optional<Usuario> opt = this.usuarioService.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Usuario usuario = this.usuarioService.atualizarImagem(opt.get(), foto);
        return ResponseEntity.ok(UsuarioDetalhesDTO.converterParaDTO(usuario));
    }

}