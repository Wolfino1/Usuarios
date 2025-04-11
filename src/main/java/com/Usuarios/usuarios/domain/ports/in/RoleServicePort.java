package com.Usuarios.usuarios.domain.ports.in;

import com.Usuarios.usuarios.domain.model.RoleModel;

import java.util.Optional;

public interface RoleServicePort {
    Optional<RoleModel> getById(Long id);
}
