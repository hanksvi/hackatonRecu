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

    /**
     * Método para autenticar al usuario mediante email y contraseña
     */
    public String login(String email, String password) throws Exception {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new Exception("Usuario no encontrado"));

        if (passwordEncoder.matches(password, usuario.getPassword())) {
            // Generamos el token pasando el email del Usuario
            return jwtService.generateToken(usuario);
        } else {
            throw new Exception("Contraseña incorrecta");
        }
    }

    /**
     * Método para registrar un nuevo usuario
     */
    public Usuario register(Usuario usuario) throws Exception {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new Exception("Ya existe un usuario con ese email");
        }
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword())); // Encriptamos la contraseña
        return usuarioRepository.save(usuario);  // Guardamos el usuario en la base de datos
    }
}