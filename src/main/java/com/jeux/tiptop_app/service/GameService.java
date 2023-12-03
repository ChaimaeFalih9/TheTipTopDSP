package com.jeux.tiptop_app.service;


import com.jeux.tiptop_app.entity.GameScore;

import java.util.List;


public interface GameService {

    void savescore(GameScore score);

    List<GameScore> findAll();

    List<GameScore> findByUser(String username);

    List<GameScore> findTopUser();

}
