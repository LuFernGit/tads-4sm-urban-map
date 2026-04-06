package br.com.senac.urbanmap.services;

import br.com.senac.urbanmap.entities.usuario.Funcao;
import br.com.senac.urbanmap.entities.usuario.Usuario;
import br.com.senac.urbanmap.entities.dtos.UsuarioDetalhesDto;
import br.com.senac.urbanmap.entities.dtos.UsuarioNovoDto;
import br.com.senac.urbanmap.exception.ErroUsuarioServiceException;
import br.com.senac.urbanmap.repositories.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;

@Service
public class UsuarioService {

    private UsuarioRepository repo;
    private PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario registrarNovoUsuario(UsuarioNovoDto dto) throws ErroUsuarioServiceException {
        if (emailCadastrado(dto)) throw new ErroUsuarioServiceException("E-mail já está cadastrado.");

        if (!idadeValida(dto))
            throw new ErroUsuarioServiceException("Cadastro autorizado somente para pessoas com no minimo 16 anos.");
        String senhaCriptografada = passwordEncoder.encode(dto.senha());
        Usuario u = Usuario.builder().nome(dto.nome()).nomeUsuario(dto.nomeUsuario()).
                dataNascimento(dto.dataNascimento()).email(dto.email()).senha(senhaCriptografada).
                funcao(Funcao.USUARIO).locaisFavoritos(new HashSet<>()).telefone(dto.telefone()).build();
        u = repo.save(u);
        return u;
    }

    private boolean idadeValida(UsuarioNovoDto dto) {
        LocalDate dataLimite = LocalDate.now().minusYears(16);
        return !dto.dataNascimento().isAfter(dataLimite);
    }

    private boolean emailCadastrado(UsuarioNovoDto dto) {
        return repo.existsByEmail(dto.email());
    }
}