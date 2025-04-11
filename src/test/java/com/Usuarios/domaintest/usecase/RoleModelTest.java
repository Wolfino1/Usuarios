package com.Usuarios.domaintest.usecase;

import com.Usuarios.usuarios.domain.model.RoleModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RoleModelTest {

    private RoleModel role;

    @BeforeEach
    void setUp() {
        role = new RoleModel(1L, "Vendedor");
    }

    @Test
    void shouldSetIdCorrectly() {
        role.setId(2L);
        assertThat(role.getId()).isEqualTo(2L);
    }

    @Test
    void shouldSetNameCorrectly() {
        role.setName("Administrador");
        assertThat(role.getName()).isEqualTo("Administrador");
    }
}

