package com.Usuarios;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UsuariosApplication {

	public static void main(String[] args) {
		String raw = "SecurePass456";
		// Si en tu setter se usa BCrypt.hashpw directamente:
		String hash = BCrypt.hashpw(raw, BCrypt.gensalt());
		boolean ok = BCrypt.checkpw(raw, hash);
		System.out.println("¿Coinciden? " + ok);
		SpringApplication.run(UsuariosApplication.class, args);
	}

}
