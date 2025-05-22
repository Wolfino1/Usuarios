package com.Usuarios.usuarios.infrastructure.security;
import com.Usuarios.usuarios.domain.model.UserModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {
    private final UserModel user;

    public CustomUserDetails(UserModel user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String roleName;
        if (user.getIdRole() == 1L) {
            roleName = "ROLE_SELLER";
        } else if (user.getIdRole() == 2L) {
            roleName = "ROLE_BUYER";
        }
        else if (user.getIdRole() == 3L) {
            roleName = "ROLE_ADMIN";
        }else {
            roleName = "ROLE_UNKNOWN";
        }
        return Collections.singletonList(new SimpleGrantedAuthority(roleName));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Personaliza esta validación según tus requerimientos
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Ajusta si gestionas bloqueo de cuenta
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Puedes agregar expiración de credenciales si es necesario
    }
    public Long getId() {
        return user.getId();
    }
    @Override
    public boolean isEnabled() {
        return true; // Modifica si tu lógica de negocio define un flag de habilitación
    }
}
