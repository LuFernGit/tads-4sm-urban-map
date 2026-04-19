package br.com.senac.urbanmap.configuration;

import br.com.senac.urbanmap.security.SecurityFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class ConfiguracaoSeguranca {
    private static final String ADMIN = "ADMIN";
    private static final String USER = "USER";
    private final SecurityFilter securityFilter;

    public ConfiguracaoSeguranca(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    // para acessar as fotos: http://localhost:5050/uploads/subpasta(usuarios/locais)/nome da foto.extensão
    // para acessar os endpoints de forma visual: http://localhost:5050/swagger-ui/index.html#

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/locais/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/usuarios/cadastro", "/usuarios/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/uploads/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/usuarios").hasRole(ADMIN)
                        .requestMatchers(HttpMethod.POST, "/tag").hasRole(ADMIN)
                        .requestMatchers(HttpMethod.PUT, "/tag").hasRole(ADMIN)
                        .requestMatchers(HttpMethod.DELETE, "/tag/**").hasRole(ADMIN)
                        .requestMatchers(HttpMethod.PUT, "/*/foto").hasAnyRole(USER, ADMIN)
                        .requestMatchers(HttpMethod.GET, "/tag").hasAnyRole(USER, ADMIN)
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}