package com.jeux.tiptop_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class FaqController {


    @GetMapping("/faq")
    public ModelAndView faq(HttpServletRequest request) {


        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");


        ModelAndView modelAndView = new ModelAndView("faq");
        modelAndView.addObject("user", "connect");
        modelAndView.addObject("name", username);

        return modelAndView;
    }
}