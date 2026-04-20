package br.com.senac.urbanmap.entities.usuario;


import br.com.senac.urbanmap.entities.local.Local;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Getter Long id;

    @Column(name = "nome", nullable = false)
    private @Getter @Setter String nome;

    @Column(name = "nome_usuario", unique = true, nullable = false)
    private @Getter
    @Setter String nomeUsuario;

    @Column(name = "foto_url")
    private @Getter
    @Setter String fotoUrl;

    @Column(name = "email", unique = true, nullable = false)
    private @Getter String email;

    @Column(name = "senha", nullable = false)
    private @Getter
    @Setter String senha;

    @Column(name = "telefone", length = 15, nullable = false)
    private @Getter
    @Setter String telefone;

    @Column(name = "data_nascimento", nullable = false)
    private @Getter LocalDate dataNascimento;

    @ManyToMany
    @JoinTable(name = "likes", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "local_id"))
    private @Getter Set<Local> likes = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "salvos", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "local_id"))
    private @Getter Set<Local> salvos = new HashSet<>();

    @Column(name = "usuario_status")
    private @Getter
    @Setter Boolean status;

    @Enumerated(EnumType.STRING)
    @Column(name = "funcao", length = 13, nullable = false)
    private @Getter Funcao funcao;

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
