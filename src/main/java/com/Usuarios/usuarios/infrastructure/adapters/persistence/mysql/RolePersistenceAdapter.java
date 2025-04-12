package com.Usuarios.usuarios.infrastructure.adapters.persistence.mysql;

import com.Usuarios.usuarios.domain.model.RoleModel;
import com.Usuarios.usuarios.domain.ports.out.RolePersistencePort;
import com.Usuarios.usuarios.infrastructure.entities.RoleEntity;
import com.Usuarios.usuarios.infrastructure.mappers.RoleEntityMapper;
import com.Usuarios.usuarios.infrastructure.repositories.mysql.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service
@Transactional
@RequiredArgsConstructor
public class RolePersistenceAdapter implements RolePersistencePort {

    private final RoleRepository roleRepository;
    private final RoleEntityMapper roleEntityMapper;

    @Override
    public Optional<RoleModel> getByRoleById(Long id) {
        Optional<RoleEntity> roleEntity = roleRepository.findById(id);
        return roleEntity.map(roleEntityMapper::entityToModel);
    }
}
