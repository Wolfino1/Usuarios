package com.Usuarios.usuarios.domain.model;

import com.Usuarios.usuarios.domain.exceptions.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.mindrot.jbcrypt.BCrypt;

public class SellerModel {

    private long id;
    private String name;
    private String lastname;
    private String document;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String email;
    private String password;
    private String role = "VENDEDOR";

    public SellerModel(long id, String name, String lastname, String document, String phoneNumber, LocalDate dateOfBirth,
                       String email, String password) {
        this.id = id;
        setName(name);
        setLastname(lastname);
        setDocument(document);
        setPhoneNumber(phoneNumber);
        setDateOfBirth(dateOfBirth);
        setEmail(email);
        setPassword(password);
        this.role = "VENDEDOR";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null) throw new NullException("El nombre");
        if (name.trim().isEmpty()) throw new EmptyException("El nombre");
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        if (lastname == null) throw new NullException("El apellido");
        if (lastname.trim().isEmpty()) throw new EmptyException("El apellido");
        this.lastname = lastname;
    }

    public String getDocument() {
        return document;
    }
        //Valida que el documento solo contenga números.
    public void setDocument(String document) {
        if (document == null) throw new NullException("El documento de identidad");
        if (document.trim().isEmpty()) throw new EmptyException("El documento de identidad");
        if (!document.matches("\\d+")) throw new IllegalArgumentException("El documento de identidad solo debe contener números");
        this.document = document;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) throw new NullException("El número de teléfono");
        if (phoneNumber.trim().isEmpty()) throw new EmptyException("El número de teléfono");
        if (phoneNumber.length() > 13) throw new MaxSizeExceededException("Número de teléfono solo puede tener 13 caracteres.");

        String phoneRegex = "^\\+?[0-9]{2}[0-9]{10}$";
        if (!phoneNumber.matches(phoneRegex)) {
            throw new IllegalArgumentException("El número de teléfono debe comenzar con '+', seguido de 2 dígitos de código de país y 10 dígitos del número.");
        }

        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    //Valida que el usuario sea mayor de edad.
    public void setDateOfBirth(LocalDate dateOfBirth) {
        if (dateOfBirth == null) {
            throw new NullException("El campo fecha de nacimiento");
        }
        if (!esMayorDeEdad(dateOfBirth)) {
            throw new UnderAgeException("El usuario vendedor");
        }
        this.dateOfBirth = dateOfBirth;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null) throw new NullException("El email");
        if (email.trim().isEmpty()) throw new EmptyException("El email");

        // Expresión regular para validar emails
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("El email no tiene un formato válido.");
        }

        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null) throw new NullException("El campo contraseña");
        if (password.trim().isEmpty()) throw new EmptyException("el campo contraseña");

        // Encriptar la contraseña con BCrypt antes de almacenarla
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }
    public boolean verificarPassword(String passwordPlano) {
        return BCrypt.checkpw(passwordPlano, this.password);
    }

    public String getRole() {
        return role;
    }

    private boolean esMayorDeEdad(LocalDate fechaNacimiento) {
        Objects.requireNonNull(fechaNacimiento, "El campo fecha de nacimiento");
        LocalDate hoy = LocalDate.now();
        return Period.between(fechaNacimiento, hoy).getYears() >= 18;
    }

}
