package com.jeux.tiptop_app.repository;

import com.jeux.tiptop_app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    @Query("SELECT u FROM User u where u.role=2")
    List<User> getAllClient();

    @Query("SELECT u FROM User u where u.email=:email")
    User getByEmail(String email);

    @Query("SELECT u FROM User u where u.name=:user")
    User getByname(String user);
}
