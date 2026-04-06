package br.com.senac.urbanmap.security;

import br.com.senac.urbanmap.repositories.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {


    private UsuarioRepository repo;

    public AutenticacaoService(UsuarioRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails u = repo.findByEmail(username);
        if (u == null) throw new UsernameNotFoundException("Usuario não encontrado");
        return u;
    }
}
