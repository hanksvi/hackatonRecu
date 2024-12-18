package com.dbp.backend.auth.filter;

import com.dbp.backend.auth.domain.UsuarioDetails;
import com.dbp.backend.auth.service.JwtService;
import com.dbp.backend.auth.service.UsuarioDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UsuarioDetailsService usuarioDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");

        String jwt = null;
        String email = null;

        logger.info("Request received at JwtAuthenticationFilter: " + request.getRequestURI());

        // Verificar si el encabezado Authorization está presente y tiene el formato adecuado
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7); // Extraer el token
            try {
                email = jwtService.extractUsername(jwt); // Extraer el email
                logger.info("JWT Token extracted: {}", jwt);
                logger.info("Extracted email: {}", email);
            } catch (Exception e) {
                logger.error("Error extracting JWT: {}", e.getMessage());
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;  // Detener la ejecución si hay un error con el JWT
            }
        }

        // Verificar si el usuario no está autenticado
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            logger.info("Email found in JWT token, proceeding with user authentication");

            // Cargar los detalles del usuario desde UsuarioDetailsService
            UserDetails userDetails = usuarioDetailsService.loadUserByUsername(email);

            // Validar el token
            if (jwtService.validateToken(jwt, userDetails)) {
                // Crear el objeto de autenticación y establecerlo en el contexto de seguridad
                var authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authToken);
                logger.info("User authenticated: {}", email);
            } else {
                logger.error("Invalid JWT Token");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;  // Detener la ejecución si el token no es válido
            }
        } else {
            logger.info("No JWT token found in request or user already authenticated");
        }

        // Continuar con la cadena de filtros
        filterChain.doFilter(request, response);
    }
}