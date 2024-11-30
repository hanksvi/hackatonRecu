package com.dbp.backend.auth.application;

import com.dbp.backend.auth.service.AuthService;
import com.dbp.backend.auth.service.JwtService;
import com.dbp.backend.usuario.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    @Autowired
    public AuthController(AuthService authService, JwtService jwtService) {
        this.authService = authService;
        this.jwtService = jwtService;
    }

    /**
     * Endpoint para loguear al usuario
     */
    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password) throws Exception {
        // Verifica las credenciales del usuario
        return authService.login(email, password);  // Llama al servicio para hacer login y obtener el token
    }

    /**
     * Endpoint para registrar un nuevo usuario
     */
    @PostMapping("/register")
    public Usuario register(@RequestBody Usuario usuario) throws Exception {
        // Llama al servicio para registrar un nuevo usuario
        return authService.register(usuario);
    }
}