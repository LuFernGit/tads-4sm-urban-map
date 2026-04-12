package br.com.senac.urbanmap.entities.usuario;


import br.com.senac.urbanmap.entities.local.Local;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Getter Long id;

    @Column(name = "nome", length = 100, nullable = false)
    private @Getter String nome;

    @Column(name = "nome_usuario", length = 100, unique = true, nullable = false)
    private @Getter
    @Setter String nomeUsuario;

    @Column(name = "imagem_url")
    private @Getter
    @Setter String imagemUrl;

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

    @ManyToMany
    @JoinTable
    private @Getter Set<Local> locaisSalvos;

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

    // do spring security
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.funcao == Funcao.ADMINISTRADOR) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    // descartado
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
