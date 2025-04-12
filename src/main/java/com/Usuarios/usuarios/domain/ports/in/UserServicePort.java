package com.Usuarios.usuarios.domain.ports.in;

import com.Usuarios.usuarios.domain.model.UserModel;

public interface UserServicePort {
    void save(UserModel userModel);
    }
