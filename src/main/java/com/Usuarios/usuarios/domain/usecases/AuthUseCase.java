package com.Usuarios.usuarios.domain.usecases;

import com.Usuarios.usuarios.application.dto.request.AuthenticationRequest;
import com.Usuarios.usuarios.application.dto.response.AuthenticationResponse;
import com.Usuarios.usuarios.domain.Utils.Constants.DomainConstants;
import com.Usuarios.usuarios.domain.model.UserModel;
import com.Usuarios.usuarios.domain.ports.in.AuthServicePort;
import com.Usuarios.usuarios.domain.ports.out.AuthPersistencePort;
import com.Usuarios.usuarios.infrastructure.security.CustomUserDetails;
import com.Usuarios.usuarios.infrastructure.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        UserModel user = authPersistencePort.findUserByEmail(request.email())
                .orElseThrow(() -> new RuntimeException(DomainConstants.USER_NOT_FOUND));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException(DomainConstants.INVALID_CREDENTIALS);
        }

        String token = jwtUtil.generateToken(new CustomUserDetails(user));

        String name = user.getName();

        return new AuthenticationResponse(token, name);
    }
}
