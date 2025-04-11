package com.Usuarios.usuarios.domain.ports.out;

import com.Usuarios.usuarios.domain.model.RoleModel;

import java.util.Optional;

public interface RolePersistencePort {
    Optional<RoleModel> getByRoleById(Long id);
}
