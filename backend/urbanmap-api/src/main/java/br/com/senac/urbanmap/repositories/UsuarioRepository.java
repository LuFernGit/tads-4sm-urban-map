package br.com.senac.urbanmap.repositories;

import br.com.senac.urbanmap.entitys.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByEmail(String email);
}