package com.dbp.backend.auth.service;

import com.dbp.backend.auth.domain.UsuarioDetails;
import com.dbp.backend.usuario.domain.Usuario;
import com.dbp.backend.usuario.infrastructure.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository baseUsuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Buscar el usuario en la base de datos por su email
        Usuario usuario = baseUsuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        // Convertir la clase Usuario a UsuarioDetails
        return new UsuarioDetails(usuario);  // Aquí devolvemos UsuarioDetails
    }
}
