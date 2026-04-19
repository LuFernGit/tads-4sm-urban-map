package br.com.senac.urbanmap.controllers;

import br.com.senac.urbanmap.controllers.dtos.UsuarioRespostaDTO;
import br.com.senac.urbanmap.controllers.dtos.UsuarioLoginDTO;
import br.com.senac.urbanmap.controllers.dtos.UsuarioCadastroDTO;
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
@RequestMapping("/usuarios")
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

//    @GetMapping("/usuarios")
//    public ResponseEntity<List<UsuarioRespostaDTO>> buscarTodos() {
//        return ResponseEntity.ok(this.usuarioService.buscarTodos());
//    }


    @PostMapping("/cadastro")
    public ResponseEntity<UsuarioRespostaDTO> cadastro(@Valid @RequestBody UsuarioCadastroDTO dto) {
        Usuario usuario = usuarioService.cadastrar(dto);

        UsuarioRespostaDTO usuarioRespostaDTO = UsuarioRespostaDTO.converterParaDTO(usuario, tokenService.geradorDeToken(usuario));

        return ResponseEntity.created(URI.create("/usuario/" + usuarioRespostaDTO.id())).body(usuarioRespostaDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioRespostaDTO> login(@RequestBody @Valid UsuarioLoginDTO dto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        Usuario usuario = (Usuario) auth.getPrincipal();
        return ResponseEntity.ok(UsuarioRespostaDTO.converterParaDTO(usuario, tokenService.geradorDeToken(usuario)));
    }

    @PutMapping("/{id}/foto") // O ID vai na URL
    public ResponseEntity<UsuarioRespostaDTO> atualizarFoto(@PathVariable Long id, @RequestParam("foto") MultipartFile foto) {
        Optional<Usuario> opt = this.usuarioService.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Usuario usuario = this.usuarioService.atualizarImagem(opt.get(), foto);
        return ResponseEntity.ok(UsuarioRespostaDTO.converterParaDTO(usuario, tokenService.geradorDeToken(usuario)));
    }

}