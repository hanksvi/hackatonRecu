package com.dbp.backend.auth.service;




import com.dbp.backend.usuario.domain.Usuario;
import com.dbp.backend.usuario.infrastructure.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;  // Verifica que este campo esté correctamente inyectado

    @Autowired
    private JwtService jwtService;

    public String login(String email, String password) throws Exception {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new Exception("Usuario no encontrado"));

        if (passwordEncoder.matches(password, Usuario.getPassword())) {
            // Generamos el token pasando el Usuario directamente
            return jwtService.generateToken(Usuario);
        } else {
            throw new Exception("Contraseña incorrecta");
        }
    }


    // Puedes añadir otros métodos aquí, como el registro de usuarios
}

