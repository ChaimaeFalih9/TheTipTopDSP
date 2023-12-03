package com.jeux.tiptop_app.api;

import com.jeux.tiptop_app.entity.GameScore;
import com.jeux.tiptop_app.entity.User;
import com.jeux.tiptop_app.service.GameService;
import com.jeux.tiptop_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author kaoutarelmofatiche
 */

@RestController
public class GameApi {

    @Autowired
    private GameService gameService;


    @GetMapping("/scoresByUser")
    public List<GameScore> scoresByUser(HttpServletRequest request, @RequestParam(value = "username") String username) {

        List<GameScore> gameScores = gameService.findByUser(username);

        return gameScores;
    }

    @GetMapping("/allScores")
    public List<GameScore> allScores() {

        List<GameScore> gameScores = gameService.findAll();

        return gameScores;
    }

}
