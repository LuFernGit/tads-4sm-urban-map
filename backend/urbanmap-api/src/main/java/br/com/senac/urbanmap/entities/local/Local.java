package br.com.senac.urbanmap.entities.local;

import br.com.senac.urbanmap.entities.tag.Tag;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "locais")
public class Local {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Getter Long id;

    @Column(name = "nome", nullable = false)
    private @Getter
    @Setter String nome;

    @Column(name = "descricao", columnDefinition = "TEXT", nullable = false)
    private @Getter
    @Setter String descricao;

    @ElementCollection
    @CollectionTable(name = "local_fotos", joinColumns = @JoinColumn(name = "local_id"))
    @Column(name = "fotos_url")
    private @Getter
    @Setter List<String> fotosUrl = new ArrayList<>();

    @Column(name = "endereco", nullable = false)
    private @Getter
    @Setter String endereco;

    @Column(name = "cidade")
    private @Getter
    @Setter String cidade;

    @Column(name = "estado", length = 2)
    private @Getter
    @Setter String estado;

    @Column(name = "cep")
    private @Getter
    @Setter String cep;

    @Column(name = "latitude", nullable = false)
    private @Getter
    @Setter BigDecimal latitude;

    @Column(name = "longitude", nullable = false)
    private @Getter
    @Setter BigDecimal longitude;

    @Column(name = "qtd_like")
    private @Getter
    @Setter Long qtdLike = 0L;

    @Column(name = "qtd_salvo")
    private @Getter
    @Setter Long qtdSalvo = 0L;

    @ManyToMany
    private @Getter Set<Tag> tags = new HashSet<>();
}