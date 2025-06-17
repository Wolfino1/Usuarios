package com.Usuarios.domaintest.usecase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import com.Usuarios.usuarios.domain.usecases.RoleUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.Usuarios.usuarios.domain.model.RoleModel;
import com.Usuarios.usuarios.domain.ports.out.RolePersistencePort;

class RoleUseCaseTest {

    private RolePersistencePort rolePersistencePort;
    private RoleUseCase roleUseCase;

    @BeforeEach
    void setUp() {
        rolePersistencePort = mock(RolePersistencePort.class);
        roleUseCase = new RoleUseCase(rolePersistencePort);
    }

    @Test
    void getById_shouldReturnRoleWhenFound() {
        RoleModel role = new RoleModel(1L, "ADMIN");
        when(rolePersistencePort.getByRoleById(1L)).thenReturn(Optional.of(role));

        Optional<RoleModel> result = roleUseCase.getById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        assertEquals("ADMIN", result.get().getName());
    }

    @Test
    void getById_shouldReturnEmptyWhenNotFound() {
        when(rolePersistencePort.getByRoleById(2L)).thenReturn(Optional.empty());

        Optional<RoleModel> result = roleUseCase.getById(2L);

        assertFalse(result.isPresent());
    }
}
