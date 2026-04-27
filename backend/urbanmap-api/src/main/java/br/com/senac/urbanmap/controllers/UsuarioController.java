package br.com.senac.urbanmap.controllers;

import br.com.senac.urbanmap.controllers.dtos.usuario.*;
import br.com.senac.urbanmap.entities.usuario.Usuario;
import br.com.senac.urbanmap.services.TokenService;
import br.com.senac.urbanmap.services.UsuarioService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
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

    // AUTENTICAÇÃO

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

    // USUARIO COMUM

    @PutMapping("/adicionar/salvo/{idUsuario}/{idLocal}")
    public ResponseEntity<?> adicionarSalvo(@PathVariable("idUsuario") Long idUsuario, @PathVariable("idLocal") Long idLocal) {
        this.usuarioService.salvarLocal(idUsuario, idLocal);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/adicionar/like/{idUsuario}/{idLocal}")
    public ResponseEntity<?> adicionaLike(@PathVariable("idUsuario") Long idUsuario, @PathVariable("idLocal") Long idLocal) {
        this.usuarioService.darLike(idUsuario, idLocal);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/remover/salvo/{idUsuario}/{idLocal}")
    public ResponseEntity<?> removerSalvo(@PathVariable("idUsuario") Long idUsuario, @PathVariable("idLocal") Long idLocal) {
        this.usuarioService.removerLocalSalvo(idUsuario, idLocal);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/remover/like/{idUsuario}/{idLocal}")
    public ResponseEntity<?> removerLike(@PathVariable("idUsuario") Long idUsuario, @PathVariable("idLocal") Long idLocal) {
        this.usuarioService.removerLike(idUsuario, idLocal);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/alterar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UsuarioRespostaDTO> atualizarInformacoes(
            @Parameter(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
            @RequestPart("usuario") UsuarioAlteracaoDTO dto,
            @RequestPart(value = "foto", required = false) MultipartFile foto) {
        Usuario u = this.usuarioService.atualizarInformacoes(dto, foto);
        return ResponseEntity.ok(UsuarioRespostaDTO.converterParaDTO(u, this.tokenService.geradorDeToken(u)));
    }

    @PutMapping("/{id}/foto")
    public ResponseEntity<UsuarioRespostaDTO> atualizarFoto(
            @PathVariable Long id,
            @Parameter(content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE))
            @RequestPart("foto") MultipartFile foto) {
        Optional<Usuario> opt = this.usuarioService.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Usuario usuario = this.usuarioService.atualizarImagem(opt.get(), foto);
        return ResponseEntity.ok(UsuarioRespostaDTO.converterParaDTO(usuario, tokenService.geradorDeToken(usuario)));
    }


    // ADMIN
    @GetMapping("/admin/listar")
    public ResponseEntity<List<UsuarioResumidoDTO>> listarTodos() {
        return ResponseEntity.ok(this.usuarioService.listarTodos());
    }

    @GetMapping("/admin/buscar")
    public ResponseEntity<?> buscarUsuario(@RequestParam("nome") String nome) {
        List<Usuario> usuarios = this.usuarioService.findByNome(nome);
        if(usuarios.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(UsuarioResumidoDTO.conveterParaListaDTO(usuarios));
    }

}