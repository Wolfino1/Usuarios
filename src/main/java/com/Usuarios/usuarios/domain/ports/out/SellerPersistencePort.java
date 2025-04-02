package com.Usuarios.usuarios.domain.ports.out;

import com.Usuarios.usuarios.domain.model.SellerModel;

public interface SellerPersistencePort {
    void save(SellerModel sellerModel);
}
