package com.Usuarios.usuarios.infrastructure.excpetionshandler;

import java.time.LocalDateTime;

public record ExceptionResponse (String message, LocalDateTime timeStamp){
}
