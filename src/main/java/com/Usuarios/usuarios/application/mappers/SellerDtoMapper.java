package com.Usuarios.usuarios.application.mappers;

import com.Usuarios.usuarios.application.dto.request.SaveSellerRequest;
import com.Usuarios.usuarios.domain.model.SellerModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SellerDtoMapper {
    SellerModel requestToModel(SaveSellerRequest saveCategoryRequest);
}
