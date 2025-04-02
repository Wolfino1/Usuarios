package com.Usuarios.applicationtest.serviceimpl;

import com.Usuarios.usuarios.application.dto.request.SaveSellerRequest;
import com.Usuarios.usuarios.application.dto.response.SaveSellerResponse;
import com.Usuarios.usuarios.application.mappers.SellerDtoMapper;
import com.Usuarios.usuarios.application.service.impl.SellerServiceImpl;
import com.Usuarios.usuarios.domain.model.SellerModel;
import com.Usuarios.usuarios.domain.ports.in.SellerServicePort;
import com.Usuarios.common.configurations.utils.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SellerServiceImplTest {

    @Mock
    private SellerServicePort sellerServicePort;

    @Mock
    private SellerDtoMapper sellerDtoMapper;

    @InjectMocks
    private SellerServiceImpl sellerServiceImpl;

    private SaveSellerRequest saveSellerRequest;
    private SellerModel sellerModel;

    @BeforeEach
    void setUp() {
        saveSellerRequest = new SaveSellerRequest(1L, "Santiago", "Guerrero", "123456789",
                "+573001234567", LocalDate.of(1995, 5, 15), "correo@example.com", "password123");

        sellerModel = new SellerModel(1L, "Santiago", "Guerrero", "123456789",
                "+573001234567", LocalDate.of(1995, 5, 15), "correo@example.com", "password123");
    }

    @Test
    void save_ShouldCallMapperAndServicePort_AndReturnResponse() {
        when(sellerDtoMapper.requestToModel(saveSellerRequest)).thenReturn(sellerModel);

        SaveSellerResponse response = sellerServiceImpl.save(saveSellerRequest);

        verify(sellerDtoMapper, times(1)).requestToModel(saveSellerRequest);
        verify(sellerServicePort, times(1)).save(sellerModel);
        assertEquals(Constants.SAVE_SELLER_RESPONSE_MESSAGE, response.message());
    }
}