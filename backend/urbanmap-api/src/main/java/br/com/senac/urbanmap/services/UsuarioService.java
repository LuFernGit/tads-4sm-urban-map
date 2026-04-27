package br.com.senac.urbanmap.services;

import br.com.senac.urbanmap.controllers.dtos.usuario.UsuarioAlteracaoDTO;
import br.com.senac.urbanmap.controllers.dtos.usuario.UsuarioResumidoDTO;
import br.com.senac.urbanmap.entities.local.Local;
import br.com.senac.urbanmap.entities.usuario.Usuario;
import br.com.senac.urbanmap.controllers.dtos.usuario.UsuarioCadastroDTO;
import br.com.senac.urbanmap.exception.ErroServiceException;
import br.com.senac.urbanmap.exception.ErroUsuarioServiceException;
import br.com.senac.urbanmap.repositories.LocalRepository;
import br.com.senac.urbanmap.repositories.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private static final String FOTO_PADRAO = "usuarios/padrao.png";
    private final UsuarioRepository usuarioRepository;
    private final LocalRepository localRepository;
    private final ImagemService imagemService;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, LocalRepository localRepository, ImagemService imagemService, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.localRepository = localRepository;
        this.imagemService = imagemService;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UsuarioResumidoDTO> listarTodos() {
        return new ArrayList<>(UsuarioResumidoDTO.conveterParaListaDTO(this.usuarioRepository.findAll()));
    }

    public Usuario cadastrar(UsuarioCadastroDTO dto) throws ErroServiceException {
        if (emailCadastrado(dto)) {
            throw new ErroUsuarioServiceException("E-mail já está cadastrado");
        }
        if (nomeUsuarioCadastrado(dto)) {
            throw new ErroUsuarioServiceException("Nome de usuário: " + dto.email() + " não está disponível");
        }
        if (!idadeValida(dto)) {
            throw new ErroUsuarioServiceException("Cadastro autorizado somente para pessoas com no minimo 16 anos");
        }
        Usuario usuario = UsuarioCadastroDTO.converterParaUsuario(dto, passwordEncoder);
        usuario.setFotoUrl(FOTO_PADRAO);
        usuario = usuarioRepository.save(usuario);
        return usuario;
    }

    // logicas de dar like e salvar local
    @Transactional(propagation = Propagation.REQUIRED)
    public void salvarLocal(Long idUsuario, Long idLocal) {
        Optional<Usuario> optUsuario = this.usuarioRepository.findById(idUsuario);
        Optional<Local> optLocal = this.localRepository.findById(idLocal);

        if (optLocal.isPresent() && optUsuario.isPresent()) {
            Usuario u = optUsuario.get();
            Local l = optLocal.get();

            if (u.getSalvos().contains(l)) throw new ErroUsuarioServiceException("Usuário já salvou esté local");

            u.getSalvos().add(l);
            l.setQtdSalvo(l.getQtdSalvo() + 1);

            this.usuarioRepository.save(u);
            this.localRepository.save(l);
        } else {
            throw new ErroUsuarioServiceException("Erro ao tentar salvar local");
        }

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void darLike(Long idUsuario, Long idLocal) {
        Optional<Usuario> optUsuario = this.usuarioRepository.findById(idUsuario);
        Optional<Local> optLocal = this.localRepository.findById(idLocal);

        if (optLocal.isPresent() && optUsuario.isPresent()) {
            Usuario u = optUsuario.get();
            Local l = optLocal.get();

            if (u.getLikes().contains(l)) throw new ErroUsuarioServiceException("Usuário já deu like neste local");

            u.getLikes().add(l);
            l.setQtdLike(l.getQtdLike() + 1);

            this.usuarioRepository.save(u);
            this.localRepository.save(l);
        } else {
            throw new ErroUsuarioServiceException("Erro ao tentar dar like no local");
        }

    }


    // logicas de remover like e local salvo
    @Transactional(propagation = Propagation.REQUIRED)
    public void removerLocalSalvo(Long idUsuario, Long idLocal) {
        Optional<Usuario> optUsuario = this.usuarioRepository.findById(idUsuario);
        Optional<Local> optLocal = this.localRepository.findById(idLocal);

        if (optLocal.isPresent() && optUsuario.isPresent()) {
            Usuario u = optUsuario.get();
            Local l = optLocal.get();

            if (!u.getSalvos().contains(l))
                throw new ErroUsuarioServiceException("Local não consta na lista de locais salvos do usuário");
            u.getSalvos().remove(l);

            if (l.getQtdSalvo() > 0) {
                l.setQtdSalvo(l.getQtdSalvo() - 1);
            }

            this.usuarioRepository.save(u);
            this.localRepository.save(l);
        } else {
            throw new ErroUsuarioServiceException("Erro ao tentar remover local salvo");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void removerLike(Long idUsuario, Long idLocal) {
        Optional<Usuario> optUsuario = this.usuarioRepository.findById(idUsuario);
        Optional<Local> optLocal = this.localRepository.findById(idLocal);

        if (optLocal.isPresent() && optUsuario.isPresent()) {
            Usuario u = optUsuario.get();
            Local l = optLocal.get();

            if (!u.getLikes().contains(l))
                throw new ErroUsuarioServiceException("Local não consta na lista de likes do usuario");
            u.getLikes().remove(l);

            if (l.getQtdLike() > 0) {
                l.setQtdLike(l.getQtdLike() - 1);
            }

            this.usuarioRepository.save(u);
            this.localRepository.save(l);
        } else {
            throw new ErroUsuarioServiceException("Erro ao tentar remover like de local");
        }
    }

    //
    public Usuario atualizarImagem(Usuario usuario, MultipartFile foto) {
        String url = this.imagemService.salvarImagem(foto, "usuarios");
        if (!usuario.getFotoUrl().equals(FOTO_PADRAO)) {
            this.imagemService.excluirImagem(usuario.getFotoUrl());
        }
        usuario.setFotoUrl(url);
        return this.usuarioRepository.save(usuario);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Usuario atualizarInformacoes(UsuarioAlteracaoDTO dto, MultipartFile foto) {
        Optional<Usuario> opt = this.usuarioRepository.findById(dto.id());
        if (opt.isEmpty()) throw new ErroUsuarioServiceException("Usuario não encontrado");
        Usuario u = opt.get();
        u.setNome(dto.nome());
        u.setNomeUsuario(dto.nomeUsuario());
        u.setTelefone(dto.telefone());
        if (foto != null && !foto.isEmpty()) {
            if (!u.getFotoUrl().equals(FOTO_PADRAO)) {
                this.imagemService.excluirImagem(u.getFotoUrl());
            }
            String url = this.imagemService.salvarImagem(foto, "usuarios");
            u.setFotoUrl(url);
        }
        this.usuarioRepository.save(u);
        return u;
    }


    private boolean idadeValida(UsuarioCadastroDTO dto) {
        LocalDate dataLimite = LocalDate.now().minusYears(16);
        return !dto.dataNascimento().isAfter(dataLimite);
    }

    private boolean emailCadastrado(UsuarioCadastroDTO dto) {
        return usuarioRepository.existsByEmail(dto.email());
    }

    private boolean nomeUsuarioCadastrado(UsuarioCadastroDTO dto) {
        return usuarioRepository.existsByNomeUsuario(dto.nomeUsuario());
    }

    public Optional<Usuario> findById(Long id) {
        return this.usuarioRepository.findById(id);
    }

    public List<Usuario> findByNome(String nome) {
        return this.usuarioRepository.findByNomeContainingIgnoreCase(nome);
    }
}