package br.com.senac.urbanmap.repositories;

import br.com.senac.urbanmap.entities.tag.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface TagRepository extends JpaRepository<Tag, Long> {
    public boolean existsByNome(String nome);

    Set<Tag> findByNomeContainingIgnoreCase(String nome);

    @Query("SELECT COALESCE(COUNT(DISTINCT t.categoria), 0) FROM Tag t")
    public Long contarCategorias();
}
