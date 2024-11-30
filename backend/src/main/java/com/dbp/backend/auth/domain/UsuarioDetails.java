package com.dbp.backend.auth.domain;

import com.dbp.backend.usuario.domain.Usuario;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UsuarioDetails implements UserDetails {
    private final Usuario usuario;
    public UsuarioDetails(Usuario Usuario) {
        this.Usuario = Usuario;

    }
    @Override
    public String getUsername() {
        return Usuario.getEmail();  // Usamos el email como nombre de usuario
    }

    @Override
    public String getPassword() {
        return Usuario.getPassword();  // Retornamos la contraseña cifrada
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Aquí puedes devolver los roles o permisos del usuario si los tienes
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  // Puedes agregar lógica para expirar la cuenta si es necesario
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  // Puedes agregar lógica para bloquear la cuenta
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // Puedes agregar lógica para expirar las credenciales
    }

    @Override
    public boolean isEnabled() {
        return true;  // Puedes deshabilitar cuentas si es necesario
    }
}

