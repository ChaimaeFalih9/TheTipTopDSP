package com.jeux.tiptop_app;

import com.jeux.tiptop_app.entity.User;
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

	public static void main(String[] args) {
		SpringApplication.run(JeuxtiptopApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String plainPassword = "123456";
		String hashedPassword = passwordEncoder.encode(plainPassword);

		if (userRepository.count() == 0) {
			// Code to create a user
			User user = new User();
			user.setUsername("Client1");
			user.setName("Client1");
			user.setPassword(hashedPassword);

			// Save the user to the database
			userRepository.save(user);
		}
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> run(args);
	}

}