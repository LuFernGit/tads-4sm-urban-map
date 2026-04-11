package br.com.senac.urbanmap.repositories;

import br.com.senac.urbanmap.entities.tag.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
        public boolean existsByNome(String nome);

}
