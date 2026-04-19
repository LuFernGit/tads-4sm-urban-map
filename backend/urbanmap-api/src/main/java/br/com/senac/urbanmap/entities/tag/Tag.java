package br.com.senac.urbanmap.entities.tag;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Getter Long id;

    @Column(name = "nome", unique = true, nullable = false)
    private @Setter
    @Getter String nome;

    @Column(name = "categoria")
    private @Getter
    @Setter
    String categoria;

    @Column(name = "cor")
    private @Getter
    @Setter String cor;
}