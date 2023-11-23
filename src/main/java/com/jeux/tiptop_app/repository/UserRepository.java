package com.jeux.tiptop_app.repository;

import com.jeux.tiptop_app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
