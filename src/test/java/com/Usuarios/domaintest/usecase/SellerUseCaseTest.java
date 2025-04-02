package com.Usuarios.domaintest.usecase;

import com.Usuarios.usuarios.domain.model.SellerModel;
import com.Usuarios.usuarios.domain.ports.out.SellerPersistencePort;
import com.Usuarios.usuarios.domain.usecases.SellerUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SellerUseCaseTest {

    @Mock
    private SellerPersistencePort sellerPersistencePort;

    @InjectMocks
    private SellerUseCase sellerUseCase;

    private SellerModel sellerModel;

    @BeforeEach
    void setUp() {
        sellerModel = new SellerModel(
                1L, "Santiago", "Guerrero", "123456789",
                "+573001234567", LocalDate.of(1995, 5, 15),
                "correo@example.com", "password123"
        );
    }

    @Test
    void save_ShouldCallPersistencePort() {
        sellerUseCase.save(sellerModel);
        verify(sellerPersistencePort, times(1)).save(sellerModel);
    }
}
