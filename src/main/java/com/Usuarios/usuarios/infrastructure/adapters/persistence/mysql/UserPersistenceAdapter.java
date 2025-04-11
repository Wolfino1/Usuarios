package com.Usuarios.usuarios.infrastructure.adapters.persistence.mysql;

import com.Usuarios.usuarios.domain.model.UserModel;
import com.Usuarios.usuarios.domain.ports.out.UserPersistencePort;
import com.Usuarios.usuarios.infrastructure.mappers.UserEntityMapper;
import com.Usuarios.usuarios.infrastructure.repositories.mysql.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserPersistencePort {
    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public void save(UserModel userModel) {
        userRepository.save(userEntityMapper.modelToEntity(userModel));
    }
}
