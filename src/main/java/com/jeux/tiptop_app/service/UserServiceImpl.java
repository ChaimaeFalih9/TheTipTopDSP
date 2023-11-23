package com.jeux.tiptop_app.service;

import com.jeux.tiptop_app.entity.User;
import com.jeux.tiptop_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User signup(String email, String username, String password) {
        User existingUser = userRepository.findByUsername(username);
        try {
            if (existingUser != null) {
                throw new IllegalArgumentException("Username already taken");
            }

            long SCORE = 0;
            long ROLE = 2;

            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String plainPassword = password;
            String hashedPassword = passwordEncoder.encode(plainPassword);

            User newUser = new User();
            newUser.setName(username);
            newUser.setScore(SCORE);
            newUser.setRole(ROLE);
            newUser.setUsername(username);
            newUser.setPassword(hashedPassword);

            return userRepository.save(newUser);

        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public String deleteUser(Long id) {
        User existingUser = userRepository.getOne(id);
        String Reponse = "Utilisateur n'existe pas";
        try {
            if(existingUser.getId()!=null){
                userRepository.deleteById(id);
                return Reponse = "Supprimé avec succès";
            }
        }catch (Exception e){
            return Reponse;
        }

        return Reponse;
    }

}
