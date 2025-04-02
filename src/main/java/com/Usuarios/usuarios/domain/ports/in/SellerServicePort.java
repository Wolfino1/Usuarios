package com.Usuarios.usuarios.domain.ports.in;

import com.Usuarios.usuarios.domain.model.SellerModel;

public interface SellerServicePort {
    void save(SellerModel sellerModel);
    }
