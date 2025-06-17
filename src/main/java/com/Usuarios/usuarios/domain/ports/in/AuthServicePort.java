package com.Usuarios.usuarios.domain.ports.in;

import com.Usuarios.usuarios.application.dto.request.AuthenticationRequest;
import com.Usuarios.usuarios.application.dto.response.AuthenticationResponse;

public interface AuthServicePort {
    AuthenticationResponse authenticate(AuthenticationRequest request);

}
