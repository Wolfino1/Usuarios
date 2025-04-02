package com.Usuarios.usuarios.application.dto.request;

import java.time.LocalDate;

public record SaveSellerRequest( long id,
 String name,
 String lastname,
 String document,
 String phoneNumber,
 LocalDate dateOfBirth,
 String email,
 String password)  {
}
