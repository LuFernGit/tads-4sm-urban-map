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
    private final SecurityFilter securityFilter;

    public ConfiguracaoSeguranca(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    // configuracao das rotas
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(csrf -> csrf.disable()).
                sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).
                authorizeHttpRequests(authorize -> authorize.
                        requestMatchers(HttpMethod.GET, "/usuarios").hasRole(ADMIN).
                        requestMatchers(HttpMethod.POST, "/cadastro").permitAll().
                        requestMatchers(HttpMethod.POST, "/login").permitAll().
                        requestMatchers(HttpMethod.GET, "/tag").hasAnyRole(ADMIN, "USER").
                        requestMatchers(HttpMethod.POST, "/tag").hasRole(ADMIN).
                        requestMatchers(HttpMethod.PUT, "/tag").hasRole(ADMIN).
                        requestMatchers(HttpMethod.DELETE, "/tag/**").hasRole(ADMIN).
                        anyRequest().authenticated()).addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    // faz a criptografia da senha do usuario
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}