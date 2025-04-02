package com.Usuarios.usuarios.domain.usecases;

import com.Usuarios.usuarios.domain.model.SellerModel;
import com.Usuarios.usuarios.domain.ports.in.SellerServicePort;
import com.Usuarios.usuarios.domain.ports.out.SellerPersistencePort;

import java.util.Optional;

public class SellerUseCase implements SellerServicePort {
    private final SellerPersistencePort sellerPersistencePort;

    public SellerUseCase(SellerPersistencePort sellerPersistencePort) {
        this.sellerPersistencePort = sellerPersistencePort;
    }

    @Override
    public void save(SellerModel sellerModel) {
        sellerPersistencePort.save(sellerModel);
    }
}
