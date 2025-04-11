package com.Usuarios.usuarios.domain.usecases;

import com.Usuarios.usuarios.domain.exceptions.EmptyException;
import com.Usuarios.usuarios.domain.model.RoleModel;
import com.Usuarios.usuarios.domain.model.UserModel;
import com.Usuarios.usuarios.domain.ports.in.UserServicePort;
import com.Usuarios.usuarios.domain.ports.out.UserPersistencePort;

import java.util.Optional;

public class UserUseCase implements UserServicePort {
    private final UserPersistencePort userPersistencePort;
    private final RoleUseCase roleUseCase;


    public UserUseCase(UserPersistencePort userPersistencePort, RoleUseCase rolementUseCase) {
        this.userPersistencePort = userPersistencePort;
        this.roleUseCase = rolementUseCase;
    }

    @Override
    public void save(UserModel userModel) {

        Optional<RoleModel> roleModel = roleUseCase.getById(userModel.getIdRole());

        if (roleModel.isEmpty()) {
            throw new EmptyException("Role not found");
        }

        userModel.setIdRole(roleModel.get().getId());
        userPersistencePort.save(userModel);
    }
}
