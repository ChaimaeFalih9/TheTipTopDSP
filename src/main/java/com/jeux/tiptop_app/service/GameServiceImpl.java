package com.jeux.tiptop_app.service;

import com.jeux.tiptop_app.entity.GameScore;
import com.jeux.tiptop_app.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GameServiceImpl implements GameService {
//test pipelinetest
    @Autowired
    GameRepository gameRepository;


    @Override
    public void savescore(GameScore gameScore) {
        gameRepository.save(gameScore);
    }

    @Override
    public List<GameScore> findAll() {
        return gameRepository.findAll();
    }

    @Override
    public List<GameScore> findByUser(String username) {
        return gameRepository.findByUser(username);
    }

    @Override
    public List<GameScore> findTopUser() {
        return gameRepository.findTopUser();
    }

}
