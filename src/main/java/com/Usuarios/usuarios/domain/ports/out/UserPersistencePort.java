package com.Usuarios.usuarios.domain.ports.out;

import com.Usuarios.usuarios.domain.model.UserModel;

public interface UserPersistencePort {
    void save(UserModel userModel);
}
