package com.Usuarios.usuarios.infrastructure.entities;

    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import java.time.LocalDate;

    @Entity
    @Data
    @Table(name = "users")
    @NoArgsConstructor
    @AllArgsConstructor
    public class SellerEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        private String name;
        private String lastname;
        private String document;
        private String phoneNumber;
        private LocalDate dateOfBirth;
        private String email;
        private String password;
        private String role = "VENDEDOR";
    }