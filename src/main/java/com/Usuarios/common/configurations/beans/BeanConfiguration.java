package com.Usuarios.common.configurations.beans;

import com.Usuarios.usuarios.domain.ports.in.RoleServicePort;
import com.Usuarios.usuarios.domain.ports.in.UserServicePort;
import com.Usuarios.usuarios.domain.ports.out.RolePersistencePort;
import com.Usuarios.usuarios.domain.ports.out.UserPersistencePort;
import com.Usuarios.usuarios.domain.usecases.RoleUseCase;
import com.Usuarios.usuarios.domain.usecases.UserUseCase;
import com.Usuarios.usuarios.infrastructure.adapters.persistence.mysql.RolePersistenceAdapter;
import com.Usuarios.usuarios.infrastructure.adapters.persistence.mysql.UserPersistenceAdapter;
import com.Usuarios.usuarios.infrastructure.mappers.RoleEntityMapper;
import com.Usuarios.usuarios.infrastructure.mappers.UserEntityMapper;
import com.Usuarios.usuarios.infrastructure.repositories.mysql.RoleRepository;
import com.Usuarios.usuarios.infrastructure.repositories.mysql.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;
    private final RoleRepository roleRepository;
    private final RoleEntityMapper roleEntityMapper;

    @Bean
    public UserServicePort userServicePort() {
        return new UserUseCase(userPersistencePort(),roleUseCase());
    }
    @Bean
    public UserPersistencePort userPersistencePort() {
        return new UserPersistenceAdapter(userRepository, userEntityMapper);
    }
    @Bean
    public RolePersistencePort rolePersistencePort(){
        return new RolePersistenceAdapter(roleRepository,roleEntityMapper);
    }
    @Bean
    public RoleServicePort roleServicePort(){return new RoleUseCase(rolePersistencePort());}

    @Bean
    public RoleUseCase roleUseCase() {
        return new RoleUseCase(rolePersistencePort());
    }
}
