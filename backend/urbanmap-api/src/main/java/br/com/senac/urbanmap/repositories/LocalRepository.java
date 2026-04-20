package br.com.senac.urbanmap.repositories;


import br.com.senac.urbanmap.entities.local.Local;
import br.com.senac.urbanmap.entities.tag.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface LocalRepository extends JpaRepository<Local, Long> {
    List<Local> findDistinctByNomeContainingIgnoreCaseOrTagsIn(String nome, Set<Tag> tags);
}
