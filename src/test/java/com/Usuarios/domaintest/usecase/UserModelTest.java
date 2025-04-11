package com.Usuarios.domaintest.usecase;

import com.Usuarios.usuarios.domain.Utils.Constants.DomainConstants;
import com.Usuarios.usuarios.domain.exceptions.*;
import com.Usuarios.usuarios.domain.model.UserModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;

class UserModelTest {

    private UserModel user;

    @BeforeEach
    void setUp() {
        user = new UserModel(1L, "Laura", "Garnica", "123456789", "+573001234567",  LocalDate.of(2000, 5, 15), "laura@example.com", "password123", 1L);
    }

    @Test
    void shouldCreateUserSuccessfully() {
        assertThat(user.getName()).isEqualTo("Laura");
        assertThat(user.getLastname()).isEqualTo("Garnica");
        assertThat(user.getDocument()).isEqualTo("123456789");
        assertThat(user.getPhoneNumber()).isEqualTo("+573001234567");
        assertThat(user.getEmail()).isEqualTo("laura@example.com");
        assertThat(user.getIdRole()).isEqualTo(1);
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        assertThatThrownBy(() -> user.setName(null))
                .isInstanceOf(NullException.class)
                .hasMessage("Field name cannot be null");
    }

    @Test
    void shouldThrowExceptionWhenNameIsEmpty() {
        assertThatThrownBy(() -> user.setName(""))
                .isInstanceOf(EmptyException.class)
                .hasMessage("Field name cannot be empty");
    }

    @Test
    void shouldThrowExceptionWhenPhoneNumberIsInvalid() {
        assertThatThrownBy(() -> user.setPhoneNumber("123"))
                .isInstanceOf(WrongArgumentException.class)
                .hasMessage(DomainConstants.WRONG_ARGUMENT_PHONE_MESSAGE);
    }

    @Test
    void shouldThrowExceptionWhenUserIsUnderage() {
        assertThatThrownBy(() -> user.setDateOfBirth(LocalDate.now().minusYears(17)))
                .isInstanceOf(UnderAgeException.class)
                .hasMessage(DomainConstants.UNDER_AGE_MESSAGE);
    }

    @Test
    void shouldEncryptPassword() {
        UserModel newUser = new UserModel(1L, "Laura", "Garnica", "123456789", "+573001234567",  LocalDate.of(2000, 5, 15), "laura@example.com", "password123", 1L);

        assertThat(newUser.getPassword()).isNotEqualTo("securePass123");
    }

    @Test
    void shouldVerifyPasswordCorrectly() {
        UserModel user = new UserModel(1L, "Laura", "Garnica", "123456789", "+573001234567",
                LocalDate.of(2000, 5, 15), "laura@example.com", "password123", 1L);

        assertThat(user.verificarPassword("password123")).isTrue();
        assertThat(user.verificarPassword("wrongpassword")).isFalse();
    }
    @Test
    void shouldSetAndGetIdCorrectly() {
        user.setId(99L);
        assertThat(user.getId()).isEqualTo(99L);
    }
    @Test
    void shouldReturnCorrectDateOfBirth() {
        LocalDate birthDate = LocalDate.of(1995, 10, 10);
        user.setDateOfBirth(birthDate);
        assertThat(user.getDateOfBirth()).isEqualTo(birthDate);
    }
    @Test
    void shouldSetDateOfBirthCorrectly() {
        LocalDate validDate = LocalDate.of(2000, 1, 1);
        user.setDateOfBirth(validDate);
        assertThat(user.getDateOfBirth()).isEqualTo(validDate);
    }
    @Test
    void shouldSetValidEmailCorrectly() {
        String validEmail = "laura.garnica@example.com";
        user.setEmail(validEmail);
        assertThat(user.getEmail()).isEqualTo(validEmail);
    }




}
