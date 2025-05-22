package com.Usuarios.usuarios.domain.ports.out;

import com.Usuarios.usuarios.domain.model.UserModel;

import java.util.Optional;

public interface AuthPersistencePort {
    Optional<UserModel> findUserByEmail(String email);
}
