package com.jeux.tiptop_app.controller;

import com.jeux.tiptop_app.entity.GameScore;
import com.jeux.tiptop_app.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author kaoutarelmofatiche
 */
@Controller
public class GameController {

    @Autowired
    GameService gameService;

    @PostMapping("/scores")
    public ResponseEntity<String> scores(@RequestBody GameScore gameScore, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        gameService.savescore(gameScore,username);
        return ResponseEntity.ok("Score saved successfully");
    }

    @GetMapping("/scores")
    public ModelAndView scores(HttpServletRequest request) {


        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        List<GameScore> gameScores = gameService.findByUser(username);

        ModelAndView modelAndView = new ModelAndView("score");
        modelAndView.addObject("user", "connect");
        modelAndView.addObject("name", username);
        modelAndView.addObject("gameScores", gameScores);

        return modelAndView;
    }

    @GetMapping("/topscores")
    public ModelAndView topscores(HttpServletRequest request) {


        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        List<GameScore> gameScores = gameService.findTopUser();

        ModelAndView modelAndView = new ModelAndView("score");
        modelAndView.addObject("user", "connect");
        modelAndView.addObject("name", username);
        modelAndView.addObject("gameScores", gameScores);

        return modelAndView;
    }

}
