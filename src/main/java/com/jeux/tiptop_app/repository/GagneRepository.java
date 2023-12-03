package com.jeux.tiptop_app.repository;

import com.jeux.tiptop_app.entity.Gagne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GagneRepository extends JpaRepository<Gagne, Long> {
    @Query(value = "SELECT g.* FROM gagne g inner join user u where g.user=u.name and u.email=:email",nativeQuery = true)
    List<Gagne> getByEmail(String email);
}
