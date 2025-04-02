package com.Usuarios.usuarios.infrastructure.adapters.persistence.mysql;

import com.Usuarios.usuarios.domain.model.SellerModel;
import com.Usuarios.usuarios.domain.ports.out.SellerPersistencePort;
import com.Usuarios.usuarios.infrastructure.mappers.SellerEntityMapper;
import com.Usuarios.usuarios.infrastructure.repositories.mysql.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SellerPersistenceAdapter implements SellerPersistencePort {
    private final SellerRepository sellerRepository;
    private final SellerEntityMapper sellerEntityMapper;

    @Override
    public void save(SellerModel sellerModel) {
        sellerRepository.save(sellerEntityMapper.modelToEntity(sellerModel));


    }
}
