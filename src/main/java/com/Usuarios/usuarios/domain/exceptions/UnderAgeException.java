package com.Usuarios.usuarios.domain.exceptions;

public class UnderAgeException extends RuntimeException {
    public UnderAgeException(String message) {
        super(message + " debe ser mayor de 18 años.");
    }
}
