package com.jeux.tiptop_app.repository;

import com.jeux.tiptop_app.entity.Role;
import com.jeux.tiptop_app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Long> {


}
