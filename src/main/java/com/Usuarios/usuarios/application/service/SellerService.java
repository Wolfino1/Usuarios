package com.Usuarios.usuarios.application.service;

import com.Usuarios.usuarios.application.dto.request.SaveSellerRequest;
import com.Usuarios.usuarios.application.dto.response.SaveSellerResponse;

public interface SellerService {
    SaveSellerResponse save(SaveSellerRequest request);
}
