package com.Usuarios.usuarios.infrastructure.security;
import com.Usuarios.usuarios.domain.Utils.Constants.DomainConstants;
import com.Usuarios.usuarios.domain.model.UserModel;
import com.Usuarios.usuarios.infrastructure.entities.UserEntity;
import com.Usuarios.usuarios.infrastructure.mappers.UserEntityMapper;
import com.Usuarios.usuarios.infrastructure.repositories.mysql.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    public CustomUserDetailsService(UserRepository userRepository, UserEntityMapper userEntityMapper) {
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(DomainConstants.USER_NOT_FOUND));
        UserModel userModel = userEntityMapper.entityToModel(userEntity);
        return new CustomUserDetails(userModel);
    }
}
