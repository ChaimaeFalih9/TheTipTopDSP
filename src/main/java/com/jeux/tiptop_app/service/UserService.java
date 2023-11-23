package com.jeux.tiptop_app.service;

import com.jeux.tiptop_app.entity.User;

import java.util.List;


public interface UserService {
    User signup(String email, String username, String password);
    List<User> getAllUsers();
    User findByUsername(String username);

    String deleteUser(Long id);
}
