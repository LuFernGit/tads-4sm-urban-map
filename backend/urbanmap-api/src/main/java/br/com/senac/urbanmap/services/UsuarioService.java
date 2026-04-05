package br.com.senac.urbanmap.services;

import br.com.senac.urbanmap.entitys.usuario.Funcao;
import br.com.senac.urbanmap.entitys.usuario.Usuario;
import br.com.senac.urbanmap.dtos.UsuarioDetalhesDto;
import br.com.senac.urbanmap.dtos.UsuarioNovoDto;
import br.com.senac.urbanmap.exception.ErroServiceException;
import br.com.senac.urbanmap.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;

@Service
public class UsuarioService {

    private UsuarioRepository repo;

    public UsuarioService(UsuarioRepository repo) {
        this.repo = repo;
    }

    public UsuarioDetalhesDto registrarUsuario(UsuarioNovoDto dto) throws ErroServiceException {
        // Trava 1: E-mail (Evita erro de banco duplicado)
        if (emailCadastrado(dto)) {
            throw new ErroServiceException("E-mail já está cadastrado.");
        }

        // Trava 2: Idade (Regra de negócio do UrbanMap)
        if (!idadeValida(dto)) {
            throw new ErroServiceException("Cadastro autorizado somente para pessoas com no minimo 16 anos.");
        }


        Usuario u = Usuario.builder().nome(dto.nome()).nomeUsuario(dto.nomeUsuario()).
                dataNascimento(dto.dataNascimento()).email(dto.email()).senha(dto.senha()).
                funcao(Funcao.USUARIO).locaisFavoritos(new HashSet<>()).telefone(dto.telefone()).build();
        u = repo.save(u);
        return new UsuarioDetalhesDto(u.getId(), u.getNome(), u.getNomeUsuario(), u.getEmail(), u.getTelefone(), u.getFuncao());
    }

    private boolean idadeValida(UsuarioNovoDto dto) {
        LocalDate dataLimite = LocalDate.now().minusYears(16);
        return !dto.dataNascimento().isAfter(dataLimite);
    }

    private boolean emailCadastrado(UsuarioNovoDto dto) {
        return repo.existsByEmail(dto.email());
    }
}