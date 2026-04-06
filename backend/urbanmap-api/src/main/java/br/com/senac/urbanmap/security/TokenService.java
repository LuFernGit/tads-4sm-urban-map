package br.com.senac.urbanmap.security;

import br.com.senac.urbanmap.entities.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    private String secret = "senac-urbanmap-api";

    public String geradorDeToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create().
                    withIssuer("urbanmap-api").
                    withExpiresAt(pegarHorarioDeExpiracao()).
                    withSubject(usuario.getEmail()).sign(algorithm);
        } catch (JWTCreationException e) {
            throw new JWTCreationException("Falha ao criar o token", e.getCause());
        }
    }

    public String validarToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm).
                    withIssuer("urbanmap-api").
                    build().verify(token).getSubject();
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    private Instant pegarHorarioDeExpiracao() {
        return LocalDateTime.now().plusDays(1).toInstant(ZoneOffset.of("-03:00"));
    }

}
