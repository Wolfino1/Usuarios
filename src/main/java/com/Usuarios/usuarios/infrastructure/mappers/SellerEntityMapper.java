package com.Usuarios.usuarios.infrastructure.mappers;

import com.Usuarios.usuarios.domain.model.SellerModel;
import com.Usuarios.usuarios.infrastructure.entities.SellerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface SellerEntityMapper {
    SellerEntity modelToEntity(SellerModel sellerModel);
}
