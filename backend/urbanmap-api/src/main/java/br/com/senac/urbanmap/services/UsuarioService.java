package br.com.senac.urbanmap.services;

import br.com.senac.urbanmap.controllers.dtos.UsuarioRespostaDTO;
import br.com.senac.urbanmap.entities.usuario.Usuario;
import br.com.senac.urbanmap.controllers.dtos.UsuarioCadastroDTO;
import br.com.senac.urbanmap.exception.ErroUsuarioServiceException;
import br.com.senac.urbanmap.repositories.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final String FOTO_PADRAO = "usuarios/padrao.png";
    private final UsuarioRepository usuarioRepo;
    private final ImagemService imagemService;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, ImagemService imagemService, PasswordEncoder passwordEncoder) {
        this.usuarioRepo = usuarioRepository;
        this.imagemService = imagemService;
        this.passwordEncoder = passwordEncoder;
    }


    public Usuario cadastrar(UsuarioCadastroDTO dto) throws ErroUsuarioServiceException {
        if (emailCadastrado(dto)) {
            throw new ErroUsuarioServiceException("E-mail já está cadastrado.");
        }
        if (nomeUsuarioCadastrado(dto)) {
            throw new ErroUsuarioServiceException("Nome de usuário indisponível");
        }
        if (!idadeValida(dto)) {
            throw new ErroUsuarioServiceException("Cadastro autorizado somente para pessoas com no minimo 16 anos.");
        }
        Usuario usuario = UsuarioCadastroDTO.converterParaUsuario(dto, passwordEncoder);
        usuario.setFotoUrl(FOTO_PADRAO);
        usuario = usuarioRepo.save(usuario);
        return usuario;
    }

    public Usuario atualizarImagem(Usuario usuario, MultipartFile foto) {
        String url = this.imagemService.salvarImagem(foto, "usuarios");
        if (!usuario.getFotoUrl().equals(FOTO_PADRAO))
            this.imagemService.excluirImagem(usuario.getFotoUrl());
        usuario.setFotoUrl(url);
        return this.usuarioRepo.save(usuario);
    }

    public void estatistica() {

    }

    // Auxiliares
    private boolean idadeValida(UsuarioCadastroDTO dto) {
        LocalDate dataLimite = LocalDate.now().minusYears(16);
        return !dto.dataNascimento().isAfter(dataLimite);
    }

    private boolean emailCadastrado(UsuarioCadastroDTO dto) {
        return usuarioRepo.existsByEmail(dto.email());
    }

    private boolean nomeUsuarioCadastrado(UsuarioCadastroDTO dto) {
        return usuarioRepo.existsBynomeUsuario(dto.nomeUsuario());
    }

    public Optional<Usuario> findById(Long id) {
        return this.usuarioRepo.findById(id);
    }
}