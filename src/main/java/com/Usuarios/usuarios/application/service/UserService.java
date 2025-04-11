package com.Usuarios.usuarios.application.service;

import com.Usuarios.usuarios.application.dto.request.SaveUserRequest;
import com.Usuarios.usuarios.application.dto.response.SaveUserResponse;

public interface UserService {
    SaveUserResponse save(SaveUserRequest request);
}
