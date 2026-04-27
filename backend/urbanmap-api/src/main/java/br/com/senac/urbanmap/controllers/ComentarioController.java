package br.com.senac.urbanmap.controllers;

import br.com.senac.urbanmap.controllers.dtos.comentario.ComentarioCadastroDTO;
import br.com.senac.urbanmap.controllers.dtos.comentario.ComentarioDTO;
import br.com.senac.urbanmap.entities.comentario.Comentario;
import br.com.senac.urbanmap.services.ComentarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/comentarios")
@CrossOrigin(origins = "*")
public class ComentarioController {

    private final ComentarioService comentarioService;

    public ComentarioController(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    @GetMapping("/{idLocal}")
    public ResponseEntity<List<ComentarioDTO>> verComentarios(@PathVariable("idLocal") Long idLocal) {
        return ResponseEntity.ok(this.comentarioService.listar(idLocal));
    }

    @PostMapping("/cadastro")
    public ResponseEntity<ComentarioDTO> fazerComentario(@RequestBody @Valid ComentarioCadastroDTO dto) {
        ComentarioDTO respostaDTO = this.comentarioService.cadastrar(dto);
        return ResponseEntity.created(URI.create("/comentarios/" + respostaDTO.id())).body(respostaDTO);
    }
}
