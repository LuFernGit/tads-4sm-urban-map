package br.com.senac.urbanmap.services;

import br.com.senac.urbanmap.entities.dtos.UsuarioDetalhesDTO;
import br.com.senac.urbanmap.entities.usuario.Usuario;
import br.com.senac.urbanmap.entities.dtos.UsuarioDadosCadastroDTO;
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


    public List<UsuarioDetalhesDTO> buscarTodos() {
        return UsuarioDetalhesDTO.converterListaParaDTO(this.usuarioRepo.findAll());
    }


    public Usuario cadastrar(UsuarioDadosCadastroDTO dto) throws ErroUsuarioServiceException {
        if (emailCadastrado(dto))
            throw new ErroUsuarioServiceException("E-mail já está cadastrado.");

        if (nomeUsuarioCadastrado(dto)) {
            throw new ErroUsuarioServiceException("Nome de usuário indisponível");
        }

        if (!idadeValida(dto))
            throw new ErroUsuarioServiceException("Cadastro autorizado somente para pessoas com no minimo 16 anos.");

        Usuario usuario = UsuarioDadosCadastroDTO.converterParaUsuario(dto, passwordEncoder);

        usuario.setImagemUrl(FOTO_PADRAO);

        usuario = usuarioRepo.save(usuario);
        return usuario;
    }

    public Usuario atualizarImagem(Usuario usuario, MultipartFile foto) {
        String url = this.imagemService.salvarImagem(foto, "usuarios");
        if (!usuario.getImagemUrl().equals(FOTO_PADRAO))
            this.imagemService.excluirImagem(usuario.getImagemUrl());
        usuario.setImagemUrl(url);
        return this.usuarioRepo.save(usuario);
    }

    // Auxiliares
    private boolean idadeValida(UsuarioDadosCadastroDTO dto) {
        LocalDate dataLimite = LocalDate.now().minusYears(16);
        return !dto.dataNascimento().isAfter(dataLimite);
    }

    private boolean emailCadastrado(UsuarioDadosCadastroDTO dto) {
        return usuarioRepo.existsByEmail(dto.email());
    }

    private boolean nomeUsuarioCadastrado(UsuarioDadosCadastroDTO dto) {
        return usuarioRepo.existsBynomeUsuario(dto.nomeUsuario());
    }

    public Optional<Usuario> findById(Long id) {
        return this.usuarioRepo.findById(id);
    }
}