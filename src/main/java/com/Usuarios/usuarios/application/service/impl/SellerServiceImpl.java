package com.Usuarios.usuarios.application.service.impl;

import com.Usuarios.usuarios.application.dto.request.SaveSellerRequest;
import com.Usuarios.usuarios.application.dto.response.SaveSellerResponse;
import com.Usuarios.usuarios.application.mappers.SellerDtoMapper;
import com.Usuarios.usuarios.application.service.SellerService;
import com.Usuarios.usuarios.domain.ports.in.SellerServicePort;
import com.Usuarios.common.configurations.utils.Constants;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {
    private final SellerServicePort sellerServicePort;
    private final SellerDtoMapper sellerDtoMapper;


    @Override
    public SaveSellerResponse save(SaveSellerRequest request) {
        sellerServicePort.save(sellerDtoMapper.requestToModel(request));
        return new SaveSellerResponse(Constants.SAVE_SELLER_RESPONSE_MESSAGE, LocalDateTime.now());    }
}
