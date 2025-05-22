package com.Usuarios.usuarios.infrastructure.adapters.persistence.mysql;

import com.Usuarios.usuarios.domain.model.UserModel;
import com.Usuarios.usuarios.domain.ports.out.AuthPersistencePort;
import com.Usuarios.usuarios.infrastructure.mappers.UserEntityMapper;
import com.Usuarios.usuarios.infrastructure.repositories.mysql.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class AuthPersistenceAdapter implements AuthPersistencePort {
    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    public AuthPersistenceAdapter(UserRepository userRepository, UserEntityMapper userEntityMapper) {
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
    }

    @Override
    public Optional<UserModel> findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userEntityMapper::entityToModel);
    }
}
