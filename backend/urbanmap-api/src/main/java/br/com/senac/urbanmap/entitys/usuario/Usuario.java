package br.com.senac.urbanmap.entitys.usuario;


import br.com.senac.urbanmap.entitys.local.Local;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Getter Long id;

    @Column(name = "nome", length = 100, nullable = false)
    private @Getter String nome;

    @Column(name = "nome_usuario", length = 100, nullable = false)
    private @Getter
    @Setter String nomeUsuario;

    @Column(name = "data_nascimento", nullable = false)
    private @Getter LocalDate dataNascimento;

    @Column(name = "email", unique = true, nullable = false)
    private @Getter String email;

    @Column(name = "senha", nullable = false)
    private @Getter
    @Setter String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "funcao", length = 13, nullable = false)
    private @Getter Funcao funcao;

    @ManyToMany
    @JoinTable(name = "locais_favoritos")
    private @Getter Set<Local> locaisFavoritos;

    @Column(name = "telefone", length = 15, nullable = false)
    private @Getter
    @Setter String telefone;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
