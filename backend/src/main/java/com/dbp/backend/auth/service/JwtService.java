package com.dbp.backend.auth.service;


import com.dbp.backend.usuario.domain.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    // Clave secreta para firmar los tokens JWT (puedes cambiarla por algo más seguro)
    private static final String SECRET_KEY = Base64.getEncoder().encodeToString(Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded());

    // Genera el token JWT utilizando el email del usuario
    public String generateToken(Usuario usuario) {
        // Extraemos el email del usuario (ya que no trabajamos con roles)
        String email = usuario.getEmail();

        // Crear los claims (si deseas agregar algo más en el futuro, como el nombre completo, por ejemplo)
        return Jwts.builder()
                .setSubject(email)  // Usamos el email como el subject
                .setIssuedAt(new Date(System.currentTimeMillis()))  // Hora de emisión
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))  // Expiración en 10 horas
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)  // Firmamos el token con la clave secreta
                .compact();
    }

    // Valida si el token es válido comparando el email extraído del token con el que se pasa como parámetro
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String email = extractUsername(token);
        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // Extrae el email del token (el "subject")
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Verifica si el token ha expirado
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Extrae la fecha de expiración del token
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Extrae cualquier claim del token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Extrae todos los claims del token JWT
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }
}