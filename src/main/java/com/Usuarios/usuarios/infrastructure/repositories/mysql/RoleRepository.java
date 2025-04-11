package com.Usuarios.usuarios.infrastructure.repositories.mysql;

import com.Usuarios.usuarios.infrastructure.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, String> {
    Optional<RoleEntity> findById(Long id);
}
