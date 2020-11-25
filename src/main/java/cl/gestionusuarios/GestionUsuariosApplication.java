package cl.gestionusuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@SpringBootApplication
public class GestionUsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionUsuariosApplication.class, args);
	}

}
