package com.Usuarios.usuarios.domain.exceptions;

public class MaxSizeExceededException extends RuntimeException {
  public MaxSizeExceededException(String message) {
    super(message);
  }
}
