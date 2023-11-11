package com.jeux.tiptop_app.service;

import com.jeux.tiptop_app.entity.User;

import java.util.List;

/**
 * @author kaoutarelmofatiche
 */
public interface UserService {
    User signup(String name, String username, String password);
    List<User> getAllUsers();
    User findByUsername(String username);
}
