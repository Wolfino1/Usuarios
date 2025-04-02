package com.Usuarios.usuarios.infrastructure.excpetionshandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.Usuarios.usuarios.domain.exceptions.*;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler(MaxSizeExceededException.class)
    public ResponseEntity<ExceptionResponse> handleMaxSizeExceededException(MaxSizeExceededException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(ExceptionConstants.MAX_SIZE_MESSAGE,
                LocalDateTime.now()));
    }
    @ExceptionHandler(NullException.class)
    public ResponseEntity<ExceptionResponse> handleNullException(NullException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(exception.getMessage(),
                LocalDateTime.now()));
    }
    @ExceptionHandler(EmptyException.class)
    public ResponseEntity<ExceptionResponse> handleEmptyException(EmptyException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(exception.getMessage(),
                LocalDateTime.now()));
    }
    @ExceptionHandler(UnderAgeException.class)
    public ResponseEntity<ExceptionResponse> handleUnderAgeException(UnderAgeException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionResponse(exception.getMessage(), LocalDateTime.now()));
    }
}
