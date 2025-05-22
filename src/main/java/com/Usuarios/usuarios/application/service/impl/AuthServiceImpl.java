package com.Usuarios.usuarios.application.service.impl;

import com.Usuarios.usuarios.application.dto.request.AuthenticationRequest;
import com.Usuarios.usuarios.application.dto.response.AuthenticationResponse;
import com.Usuarios.usuarios.application.service.AuthService;
import com.Usuarios.usuarios.domain.ports.in.AuthServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthServicePort authServicePort;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        return authServicePort.authenticate(request);
    }
}