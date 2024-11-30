package com.dbp.backend.auth.service;

import com.dbp.backend.usuario.domain.Usuario;
import com.dbp.backend.usuario.infrastructure.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository baseAppUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Buscar el usuario en la base de datos
        Usuario usuario = baseAppUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        // Convertir la clase AppUser a UserDetails
        return new org.springframework.security.core.userdetails.User(
                usuario.getEmail(),
                usuario.getPassword(),
                getAuthorities(appUser)
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Usuario usuario) {
        // Convertir el rol a una lista de GrantedAuthority
        return Collections.singletonList(new SimpleGrantedAuthority(usuario.getRole().name()));
    }
}
