package com.felipearruda.security;

import com.felipearruda.security.domain.Role;
import com.felipearruda.security.domain.Usuario;
import com.felipearruda.security.service.UsuarioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner executar(UsuarioService usuarioService){
		return args -> {
			usuarioService.salvarRole(new Role(null, "ROLE_USER"));
			usuarioService.salvarRole(new Role(null, "ROLE_MANAGER"));
			usuarioService.salvarRole(new Role(null, "ROLE_ADMIN"));
			usuarioService.salvarRole(new Role(null, "ROLE_SUPER_ADMIN"));

			usuarioService.salvarUsuario(new Usuario(null, "John Travolta", "john", "1234", new ArrayList<>()));
			usuarioService.salvarUsuario(new Usuario(null, "Will Smith", "will", "1234", new ArrayList<>()));
			usuarioService.salvarUsuario(new Usuario(null, "Jim Carrey", "jim", "1234", new ArrayList<>()));
			usuarioService.salvarUsuario(new Usuario(null, "Adam Sandler", "adam", "1234", new ArrayList<>()));

			usuarioService.addRoleParaUsuario("john", "ROLE_USER");
			usuarioService.addRoleParaUsuario("john", "ROLE_MANAGER");
			usuarioService.addRoleParaUsuario("will", "ROLE_MANAGER");
			usuarioService.addRoleParaUsuario("jim", "ROLE_ADMIN");
			usuarioService.addRoleParaUsuario("adam", "ROLE_SUPER_ADMIN");
			usuarioService.addRoleParaUsuario("adam", "ROLE_ADMIN");
			usuarioService.addRoleParaUsuario("adam", "ROLE_USER");
		};
	}

}
