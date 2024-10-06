package br.com.school.product.api.security;

import br.com.school.product.domain.product.UsuarioService;
import br.com.school.product.domain.product.entity.UsuarioEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class TokenService {
    static final String HEADER_STRING = "Authorization";

    private static final String TOKEN_PREFIX = "Bearer";

    @Value("${jwt.expiration}")
    private String expiration;

    @Value("${jwt.secret}")
    private String secret;
    private final UsuarioService usuarioService;

    public String generateToken(UsuarioEntity usuarioEntity) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + Long.parseLong(expiration));

        return TOKEN_PREFIX + " " +
                Jwts.builder()
                        .setIssuer("pessoa-api")
                        .claim(Claims.ID, usuarioEntity.getIdUsuario().toString())
                        .setIssuedAt(now)
                        .setExpiration(exp)
                        .signWith(SignatureAlgorithm.HS256, secret)
                        .compact();
    }

    public UsernamePasswordAuthenticationToken isValid(String token) {
        log.debug("usernamePasswordAuthentication");
        if (token != null) {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();
            String user = body.get(Claims.ID, String.class);
            if (user != null) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
                return usernamePasswordAuthenticationToken;
            }
        }
        return null;
    }

}
