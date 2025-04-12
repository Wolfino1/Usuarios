package com.Usuarios.domaintest.usecase;



import com.Usuarios.usuarios.domain.exceptions.EmptyException;
import com.Usuarios.usuarios.domain.model.RoleModel;
import com.Usuarios.usuarios.domain.model.UserModel;
import com.Usuarios.usuarios.domain.ports.out.UserPersistencePort;
import com.Usuarios.usuarios.domain.usecases.RoleUseCase;
import com.Usuarios.usuarios.domain.usecases.UserUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserUseCaseTest {

    @Mock
    private UserPersistencePort userPersistencePort;

    @Mock
    private RoleUseCase roleUseCase;

    @InjectMocks
    private UserUseCase userUseCase;

    private UserModel userModel;
    private RoleModel roleModel;

    @BeforeEach
    void setUp() {
        userModel = new UserModel(1L, "Laura", "Garnica", "123456789", "+573001234567",  LocalDate.of(2000, 5, 15), "laura@example.com", "password123", 1L);
        roleModel = new RoleModel(1L, "Seller");
    }

    @Test
    void save_WhenRoleExists_ShouldSaveUser() {
        when(roleUseCase.getById(1L)).thenReturn(Optional.of(roleModel));

        assertDoesNotThrow(() -> userUseCase.save(userModel));
        verify(userPersistencePort, times(1)).save(userModel);
    }

    @Test
    void save_WhenRoleDoesNotExist_ShouldThrowException() {
        when(roleUseCase.getById(1L)).thenReturn(Optional.empty());

        EmptyException exception = assertThrows(EmptyException.class, () -> userUseCase.save(userModel));
        assertEquals("Role not found", exception.getMessage());
        verify(userPersistencePort, never()).save(any());
    }
}
