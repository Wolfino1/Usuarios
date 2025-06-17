package com.Usuarios.domaintest.usecase;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.Usuarios.usuarios.domain.exceptions.EmptyException;
import com.Usuarios.usuarios.domain.exceptions.MaxSizeExceededException;
import com.Usuarios.usuarios.domain.exceptions.NullException;
import com.Usuarios.usuarios.domain.exceptions.UnderAgeException;
import com.Usuarios.usuarios.domain.exceptions.WrongArgumentException;
import com.Usuarios.usuarios.domain.model.UserModel;

class UserModelTest {

    @Test
    void constructor_shouldCreateWhenAllValid() {
        UserModel user = new UserModel(
                1L,
                "John",
                "Doe",
                "123456789",
                "+011234567890",
                LocalDate.now().minusYears(20),
                "john.doe@example.com",
                "password",
                2L
        );
        assertEquals(1L, user.getId());
        assertEquals("John", user.getName());
        assertEquals("Doe", user.getLastname());
        assertEquals("123456789", user.getDocument());
        assertEquals("+011234567890", user.getPhoneNumber());
        assertEquals("john.doe@example.com", user.getEmail());
    }

    @Test
    void setName_shouldThrowWhenNull() {
        assertThrows(NullException.class, () -> new UserModel(
                1L, null, "Doe", "123456789", "+011234567890",
                LocalDate.now().minusYears(20), "a@b.com", "pass", 2L
        ));
    }

    @Test
    void setName_shouldThrowWhenEmpty() {
        assertThrows(EmptyException.class, () -> new UserModel(
                1L, "   ", "Doe", "123456789", "+011234567890",
                LocalDate.now().minusYears(20), "a@b.com", "pass", 2L
        ));
    }

    @Test
    void setLastname_shouldThrowWhenNull() {
        assertThrows(NullException.class, () -> new UserModel(
                1L, "John", null, "123456789", "+011234567890",
                LocalDate.now().minusYears(20), "a@b.com", "pass", 2L
        ));
    }

    @Test
    void setLastname_shouldThrowWhenEmpty() {
        assertThrows(EmptyException.class, () -> new UserModel(
                1L, "John", "   ", "123456789", "+011234567890",
                LocalDate.now().minusYears(20), "a@b.com", "pass", 2L
        ));
    }

    @Test
    void setDocument_shouldThrowWhenNull() {
        assertThrows(NullException.class, () -> new UserModel(
                1L, "John", "Doe", null, "+011234567890",
                LocalDate.now().minusYears(20), "a@b.com", "pass", 2L
        ));
    }

    @Test
    void setDocument_shouldThrowWhenEmpty() {
        assertThrows(EmptyException.class, () -> new UserModel(
                1L, "John", "Doe", "   ", "+011234567890",
                LocalDate.now().minusYears(20), "a@b.com", "pass", 2L
        ));
    }

    @Test
    void setDocument_shouldThrowWhenNonDigits() {
        assertThrows(WrongArgumentException.class, () -> new UserModel(
                1L, "John", "Doe", "ABC123", "+011234567890",
                LocalDate.now().minusYears(20), "a@b.com", "pass", 2L
        ));
    }

    @Test
    void setPhoneNumber_shouldThrowWhenNull() {
        assertThrows(NullException.class, () -> new UserModel(
                1L, "John", "Doe", "123456789", null,
                LocalDate.now().minusYears(20), "a@b.com", "pass", 2L
        ));
    }

    @Test
    void setPhoneNumber_shouldThrowWhenEmpty() {
        assertThrows(EmptyException.class, () -> new UserModel(
                1L, "John", "Doe", "123456789", "   ",
                LocalDate.now().minusYears(20), "a@b.com", "pass", 2L
        ));
    }

    @Test
    void setPhoneNumber_shouldThrowWhenTooLong() {
        String longPhone = "+01" + "0".repeat(12);
        assertThrows(MaxSizeExceededException.class, () -> new UserModel(
                1L, "John", "Doe", "123456789", longPhone,
                LocalDate.now().minusYears(20), "a@b.com", "pass", 2L
        ));
    }

    @Test
    void setPhoneNumber_shouldThrowWhenInvalidFormat() {
        assertThrows(WrongArgumentException.class, () -> new UserModel(
                1L, "John", "Doe", "123456789", "12345",
                LocalDate.now().minusYears(20), "a@b.com", "pass", 2L
        ));
    }

    @Test
    void setDateOfBirth_shouldThrowWhenNull() {
        assertThrows(NullException.class, () -> new UserModel(
                1L, "John", "Doe", "123456789", "+011234567890",
                null, "a@b.com", "pass", 2L
        ));
    }

    @Test
    void setDateOfBirth_shouldThrowWhenUnderage() {
        LocalDate minor = LocalDate.now().minusYears(17);
        assertThrows(UnderAgeException.class, () -> new UserModel(
                1L, "John", "Doe", "123456789", "+011234567890",
                minor, "a@b.com", "pass", 2L
        ));
    }

    @Test
    void setEmail_shouldThrowWhenNull() {
        assertThrows(NullException.class, () -> new UserModel(
                1L, "John", "Doe", "123456789", "+011234567890",
                LocalDate.now().minusYears(20), null, "pass", 2L
        ));
    }

    @Test
    void setEmail_shouldThrowWhenEmpty() {
        assertThrows(EmptyException.class, () -> new UserModel(
                1L, "John", "Doe", "123456789", "+011234567890",
                LocalDate.now().minusYears(20), "   ", "pass", 2L
        ));
    }

    @Test
    void setEmail_shouldThrowWhenInvalidFormat() {
        assertThrows(WrongArgumentException.class, () -> new UserModel(
                1L, "John", "Doe", "123456789", "+011234567890",
                LocalDate.now().minusYears(20), "invalid-email", "pass", 2L
        ));
    }

    @Test
    void setPassword_shouldThrowWhenNull() {
        assertThrows(NullException.class, () -> new UserModel(
                1L, "John", "Doe", "123456789", "+011234567890",
                LocalDate.now().minusYears(20), "a@b.com", null, 2L
        ));
    }

    @Test
    void setPassword_shouldThrowWhenEmpty() {
        assertThrows(EmptyException.class, () -> new UserModel(
                1L, "John", "Doe", "123456789", "+011234567890",
                LocalDate.now().minusYears(20), "a@b.com", "   ", 2L
        ));
    }
}

