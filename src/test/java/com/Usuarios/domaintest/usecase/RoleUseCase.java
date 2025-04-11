package com.Usuarios.domaintest.usecase;

import com.Usuarios.usuarios.domain.model.RoleModel;
import com.Usuarios.usuarios.domain.ports.out.RolePersistencePort;
import com.Usuarios.usuarios.domain.usecases.RoleUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class RoleUseCaseTest {

    private RoleUseCase roleUseCase;

    @Mock
    private RolePersistencePort rolePersistencePort;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        roleUseCase = new RoleUseCase(rolePersistencePort);
    }

    @Test
    void shouldReturnRoleWhenIdExists() {
        RoleModel role = new RoleModel(1L, "Vendedor");
        when(rolePersistencePort.getByRoleById(1L)).thenReturn(Optional.of(role));

        Optional<RoleModel> result = roleUseCase.getById(1L);

        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(1L);
        assertThat(result.get().getName()).isEqualTo("Vendedor");

        verify(rolePersistencePort, times(1)).getByRoleById(1L);
    }

    @Test
    void shouldReturnEmptyWhenIdDoesNotExist() {
        when(rolePersistencePort.getByRoleById(99L)).thenReturn(Optional.empty());

        Optional<RoleModel> result = roleUseCase.getById(99L);

        assertThat(result).isEmpty();

        verify(rolePersistencePort, times(1)).getByRoleById(99L);
    }
}
