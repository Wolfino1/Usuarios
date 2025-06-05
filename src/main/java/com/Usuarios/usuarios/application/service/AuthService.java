package com.Usuarios.usuarios.application.service;

import com.Usuarios.usuarios.application.dto.request.AuthenticationRequest;
import com.Usuarios.usuarios.application.dto.response.AuthenticationResponse;

public interface AuthService {
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
