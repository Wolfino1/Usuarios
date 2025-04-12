package com.Usuarios.usuarios.infrastructure.mappers;

import com.Usuarios.usuarios.domain.model.RoleModel;
import com.Usuarios.usuarios.infrastructure.entities.RoleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface RoleEntityMapper {
    RoleModel entityToModel(RoleEntity roleEntity);

}
