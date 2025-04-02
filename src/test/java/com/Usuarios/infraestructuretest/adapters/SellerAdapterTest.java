package com.Usuarios.infraestructuretest.adapters;

import com.Usuarios.usuarios.domain.model.SellerModel;
import com.Usuarios.usuarios.infrastructure.adapters.persistence.mysql.SellerPersistenceAdapter;
import com.Usuarios.usuarios.infrastructure.entities.SellerEntity;
import com.Usuarios.usuarios.infrastructure.mappers.SellerEntityMapper;
import com.Usuarios.usuarios.infrastructure.repositories.mysql.SellerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SellerPersistenceAdapterTest {

    @Mock
    private SellerRepository sellerRepository;

    @Mock
    private SellerEntityMapper sellerEntityMapper;

    @InjectMocks
    private SellerPersistenceAdapter sellerPersistenceAdapter;

    private SellerModel sellerModel;
    private SellerEntity sellerEntity;

    @BeforeEach
    void setUp() {
        sellerModel = new SellerModel(1L, "Juan", "Pérez", "12345678", "+573101234567",  LocalDate.of(1990, 1, 1), "juan@example.com", "password123");
        sellerEntity = new SellerEntity(1L, "Juan", "Pérez", "12345678", "+583101234567",  LocalDate.of(1990, 1, 1), "juan@example.com", "password123", "VENDEDOR");
    }

    @Test
    void save_ShouldSaveSellerEntity() {
        when(sellerEntityMapper.modelToEntity(sellerModel)).thenReturn(sellerEntity);

        sellerPersistenceAdapter.save(sellerModel);

        verify(sellerEntityMapper, times(1)).modelToEntity(sellerModel);
        verify(sellerRepository, times(1)).save(sellerEntity);
    }
}