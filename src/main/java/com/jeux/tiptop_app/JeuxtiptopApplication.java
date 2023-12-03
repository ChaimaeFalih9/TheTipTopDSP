package com.jeux.tiptop_app;

import com.jeux.tiptop_app.entity.Role;
import com.jeux.tiptop_app.entity.User;
import com.jeux.tiptop_app.repository.RoleRepository;
import com.jeux.tiptop_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class JeuxtiptopApplication implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(JeuxtiptopApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		if (roleRepository.count() == 0) {
			Role role = new Role();
			role.setName("ADMIN");
			roleRepository.save(role);

			Role role1 = new Role();
			role1.setName("CLIENT");
			roleRepository.save(role1);


			Role role2 = new Role();
			role2.setName("EMPLOYE");
			roleRepository.save(role2);

		}

		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String plainPassword = "123456";
		String hashedPassword = passwordEncoder.encode(plainPassword);
		if (userRepository.count() == 0) {
			// Code to create a user
			User user = new User();
			user.setUsername("Client1");
			user.setName("Client1");
			user.setEmail("Client1@gmail.com");
			user.setRole(2L);
			user.setPassword(hashedPassword);

			// Save the user to the database
			userRepository.save(user);

			User user1 = new User();
			user1.setUsername("Employe");
			user1.setName("Employe");
			user1.setEmail("Employe@gmail.com");
			user1.setRole(3L);
			user1.setPassword(hashedPassword);

			// Save the user to the database
			userRepository.save(user1);

			User user2 = new User();
			user2.setUsername("Admin");
			user2.setName("Admin");
			user.setEmail("Admin@gmail.com");
			user2.setRole(1L);
			user2.setPassword(hashedPassword);

			// Save the user to the database
			userRepository.save(user2);
		}
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> run(args);
	}

}
