package com.Usuarios.usuarios.domain.usecases;

import com.Usuarios.usuarios.domain.model.RoleModel;
import com.Usuarios.usuarios.domain.ports.in.RoleServicePort;
import com.Usuarios.usuarios.domain.ports.out.RolePersistencePort;

import java.util.Optional;

public class RoleUseCase implements RoleServicePort {
    private final RolePersistencePort rolePersistencePort;

    public RoleUseCase(RolePersistencePort rolePersistencePort) {
        this.rolePersistencePort = rolePersistencePort;
    }

    @Override
    public Optional<RoleModel> getById(Long id) {
        return rolePersistencePort.getByRoleById(id);
    }
}
