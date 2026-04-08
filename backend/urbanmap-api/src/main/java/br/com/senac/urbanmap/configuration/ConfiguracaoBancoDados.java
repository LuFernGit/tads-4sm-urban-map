package br.com.senac.urbanmap.configuration;

import br.com.senac.urbanmap.entities.usuario.Funcao;
import br.com.senac.urbanmap.entities.usuario.Usuario;
import br.com.senac.urbanmap.repositories.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@Configuration
public class ConfiguracaoBancoDados {

    UsuarioRepository usuarioRepo;
    PasswordEncoder encoder;

    public ConfiguracaoBancoDados(UsuarioRepository usuarioRepo, PasswordEncoder encoder) {
        this.usuarioRepo = usuarioRepo;
        this.encoder = encoder;
    }

    @Bean
    CommandLineRunner configuracaoInicial() {
        return args -> {
            if (usuarioRepo.count() == 0) {
                Usuario admin = Usuario.builder().nome("Administrador").
                        nomeUsuario("Admin").dataNascimento(LocalDate.now()).email("admin.urbanmap@gmail.com").
                        senha(encoder.encode("admin1234")).funcao(Funcao.ADMINISTRADOR).telefone("119999999999").build();
                usuarioRepo.save(admin);
            }
        };
    }
}