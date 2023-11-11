package com.jeux.tiptop_app.controller;

import com.jeux.tiptop_app.entity.User;
import com.jeux.tiptop_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * @author kaoutarelmofatiche
 */
@Controller
public class LoginController {
//test branch
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;



    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(Model model,HttpServletRequest request, Principal principal) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userService.findByUsername(username);

        //authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
                model.addAttribute("user", "connect");
                model.addAttribute("name", user.getUsername());
                return "index";
            } else {
            return "login";
        }

    }

}
