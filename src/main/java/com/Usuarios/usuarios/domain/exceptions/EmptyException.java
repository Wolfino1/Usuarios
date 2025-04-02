package com.Usuarios.usuarios.domain.exceptions;

public class EmptyException extends RuntimeException {
    public EmptyException(String message) {
        super(message + " no puede estar vacío");
    }
}
