package com.Usuarios.usuarios.application.mappers;

import com.Usuarios.usuarios.application.dto.request.SaveUserRequest;
import com.Usuarios.usuarios.domain.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserDtoMapper {
    UserModel requestToModel(SaveUserRequest saveUserRequest);
}
