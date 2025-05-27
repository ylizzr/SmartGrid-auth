package com.energiainteligente.authservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableWebSecurity
public class AuthserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthserviceApplication.class, args);
	}
	/*@Bean
	public CommandLineRunner run() {
		return args -> {
			String hash = new BCryptPasswordEncoder().encode("12345");
			System.out.println("Contraseña codificada: " + hash);
		};
	}*/

}
