package br.com.senac.urbanmap.repositories;

import br.com.senac.urbanmap.entities.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByEmail(String email);

    boolean existsByNomeUsuario(String nomeUsuario);

    UserDetails findByEmail(String email);

    List<Usuario> findByLikesId(Long idLocal);

    List<Usuario> findBySalvosId(Long idLocal);

    List<Usuario> findByNomeContainingIgnoreCase(String nome);
}