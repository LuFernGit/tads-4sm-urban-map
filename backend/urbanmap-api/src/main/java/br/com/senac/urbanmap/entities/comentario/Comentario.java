package br.com.senac.urbanmap.entities.comentario;


import br.com.senac.urbanmap.entities.local.Local;
import br.com.senac.urbanmap.entities.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "comentarios")
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Getter Long id;

    @ManyToOne(optional = false)
    private @Getter Usuario autor;

    @ManyToOne(optional = false)
    private @Getter Local local;

    @Column(name = "conteudo", length = 300, nullable = false)
    private @Getter
    @Setter String conteudo;

    @Column(name = "data_criacao", nullable = false, updatable = false)
    private @Getter
    @Setter LocalDateTime dataCriacao = LocalDateTime.now();
}
