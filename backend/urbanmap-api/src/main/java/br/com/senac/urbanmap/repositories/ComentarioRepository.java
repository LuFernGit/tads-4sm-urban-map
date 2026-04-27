package br.com.senac.urbanmap.repositories;


import br.com.senac.urbanmap.entities.comentario.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    public List<Comentario> findByLocalId(Long localId);
    public boolean existsByAutorIdAndLocalId(Long autorId, Long localId);
}
