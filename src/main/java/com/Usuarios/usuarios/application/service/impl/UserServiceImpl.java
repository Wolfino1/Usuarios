package com.Usuarios.usuarios.application.service.impl;

import com.Usuarios.usuarios.application.dto.request.SaveUserRequest;
import com.Usuarios.usuarios.application.dto.response.SaveUserResponse;
import com.Usuarios.usuarios.application.mappers.UserDtoMapper;
import com.Usuarios.usuarios.application.service.UserService;
import com.Usuarios.usuarios.domain.model.UserModel;
import com.Usuarios.usuarios.domain.ports.in.UserServicePort;
import com.Usuarios.common.configurations.utils.Constants;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserServicePort userServicePort;
    private final UserDtoMapper userDtoMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public SaveUserResponse save(SaveUserRequest request) {
        String encodedPassword = passwordEncoder.encode(request.password());

        UserModel user = userDtoMapper.requestToModel(request);
        user.setPassword(encodedPassword,user.getPassword());

        userServicePort.save(user);
        return new SaveUserResponse(Constants.SAVE_USER_RESPONSE_MESSAGE, LocalDateTime.now());
    }
}
