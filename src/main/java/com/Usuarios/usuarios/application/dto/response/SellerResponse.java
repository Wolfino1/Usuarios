package com.Usuarios.usuarios.application.dto.response;

import java.time.LocalDate;

public record SellerResponse (Long id, String name,
                                  String lastname,
                                  String document,
                                  String phoneNumber,
                                  LocalDate dateOfBirth,
                                  String email,
                                  String password, String role){
}