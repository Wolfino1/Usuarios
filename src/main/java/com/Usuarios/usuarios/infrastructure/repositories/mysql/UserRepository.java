package com.Usuarios.usuarios.infrastructure.repositories.mysql;

import com.Usuarios.usuarios.infrastructure.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
