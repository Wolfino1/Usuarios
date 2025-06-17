package com.Usuarios.domaintest.usecase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Optional;

import com.Usuarios.usuarios.domain.usecases.AuthUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.Usuarios.usuarios.application.dto.request.AuthenticationRequest;
import com.Usuarios.usuarios.application.dto.response.AuthenticationResponse;
import com.Usuarios.usuarios.domain.model.UserModel;
import com.Usuarios.usuarios.domain.ports.out.AuthPersistencePort;
import com.Usuarios.usuarios.infrastructure.security.CustomUserDetails;
import com.Usuarios.usuarios.infrastructure.security.JwtUtil;

class AuthUseCaseTest {

    private AuthPersistencePort authPersistencePort;
    private PasswordEncoder passwordEncoder;
    private JwtUtil jwtUtil;
    private AuthUseCase authUseCase;

    @BeforeEach
    void setUp() {
        authPersistencePort = mock(AuthPersistencePort.class);
        passwordEncoder = mock(PasswordEncoder.class);
        jwtUtil = mock(JwtUtil.class);
        authUseCase = new AuthUseCase(authPersistencePort, passwordEncoder, jwtUtil);
    }

    @Test
    void authenticate_shouldThrowWhenUserNotFound() {
        AuthenticationRequest request = new AuthenticationRequest("missing@example.com", "anyPass");
        when(authPersistencePort.findUserByEmail("missing@example.com")).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> authUseCase.authenticate(request)
        );
        assertEquals("Usuario no encontrado", ex.getMessage());
    }

    @Test
    void authenticate_shouldThrowWhenPasswordMismatch() {
        UserModel user = new UserModel(
                1L,
                "Test",
                "User",
                "123456789",
                "+011234567890",
                LocalDate.of(2000, 1, 1),
                "test@example.com",
                "encodedPassword",
                1L
        );
        AuthenticationRequest request = new AuthenticationRequest("test@example.com", "wrongPass");
        when(authPersistencePort.findUserByEmail("test@example.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("wrongPass", "encodedPassword")).thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> authUseCase.authenticate(request)
        );
        assertEquals("Credenciales inválidas (Hash no coincide)", ex.getMessage());
    }

    @Test
    void authenticate_shouldReturnTokenAndNameWhenValid() {
        UserModel user = new UserModel(
                2L,
                "Jane",
                "Doe",
                "987654321",
                "+019876543210",
                LocalDate.of(1995, 5, 5),
                "jane.doe@example.com",
                "hashedPass",
                2L
        );
        AuthenticationRequest request = new AuthenticationRequest("jane.doe@example.com", "correctPass");
        when(authPersistencePort.findUserByEmail("jane.doe@example.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("correctPass", "hashedPass")).thenReturn(true);
        when(jwtUtil.generateToken(any(CustomUserDetails.class))).thenReturn("fakeJwtToken");

        AuthenticationResponse response = authUseCase.authenticate(request);

        assertEquals("fakeJwtToken", response.token());
        assertEquals("Jane", response.name());
    }
}

