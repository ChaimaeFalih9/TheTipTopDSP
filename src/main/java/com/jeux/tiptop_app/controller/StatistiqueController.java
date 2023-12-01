package com.jeux.tiptop_app.controller;

import com.jeux.tiptop_app.entity.User;
import com.jeux.tiptop_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class StatistiqueController {
//test junkins

    @Autowired
    private UserService userService;

    @GetMapping("/statistique")
    public ModelAndView statistique(HttpServletRequest request,@RequestParam(value = "user") String user) {


        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        User userByName = userService.findByUsername(user);


        ModelAndView modelAndView = new ModelAndView("statistique");
        modelAndView.addObject("user", "connect");
        modelAndView.addObject("name", user);
        modelAndView.addObject("role", userByName.getRole());

        return modelAndView;
    }
}
