package com.jeux.tiptop_app.repository;

import com.jeux.tiptop_app.entity.GameScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author kaoutarelmofatiche
 */
public interface GameRepository extends JpaRepository<GameScore, Long> {

    @Query("SELECT g FROM GameScore g WHERE g.user = :username")
    List<GameScore> findByUser(String username);

    @Query("SELECT g FROM GameScore g order by g.score asc")
    List<GameScore> findTopUser();
}
