package com.Usuarios.usuarios.domain.usecases;

import com.Usuarios.usuarios.application.dto.request.AuthenticationRequest;
import com.Usuarios.usuarios.application.dto.response.AuthenticationResponse;
import com.Usuarios.usuarios.domain.model.UserModel;
import com.Usuarios.usuarios.domain.ports.in.AuthServicePort;
import com.Usuarios.usuarios.domain.ports.out.AuthPersistencePort;
import com.Usuarios.usuarios.infrastructure.security.CustomUserDetails;
import com.Usuarios.usuarios.infrastructure.security.JwtUtil;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class AuthUseCase implements AuthServicePort {

    private final AuthPersistencePort authPersistencePort;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthUseCase(AuthPersistencePort authPersistencePort, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.authPersistencePort = authPersistencePort;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        // Debug 1: Verifica contraseña recibida
        byte[] passwordBytes = request.password().getBytes(StandardCharsets.UTF_8);
        UserModel user = authPersistencePort.findUserByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Debug 2: Comparación manual
        boolean manualCheck = BCrypt.checkpw(request.password(), user.getPassword());

        // Debug 3: Genera un nuevo hash para comparar
        String newHash = passwordEncoder.encode(request.password());

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("Credenciales inválidas (Hash no coincide)");
        }

        return new AuthenticationResponse(jwtUtil.generateToken(new CustomUserDetails(user)));
    }
}
