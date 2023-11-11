package com.jeux.tiptop_app.controller;

import com.jeux.tiptop_app.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.jeux.tiptop_app.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

/**
 * @author kaoutarelmofatiche
 */

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @Value("${code.validation}")
    private String codeValidation;


    @GetMapping("/")
    public String index(Principal principal, Model model) {


        if (principal instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken authenticationToken = (OAuth2AuthenticationToken) principal;
            OAuth2User oauth2User = authenticationToken.getPrincipal();

            String firstName = oauth2User.getAttribute("given_name");
            String lastName = oauth2User.getAttribute("family_name");
            String email = oauth2User.getAttribute("email");

            // Utilisation des valeurs récupérées
            model.addAttribute("user", "connect");
            model.addAttribute("name", firstName);
        } else{
            model.addAttribute("user", "noconnect");
            model.addAttribute("name", "username");
        }

        return "index";
    }
    @PostMapping("/loginm")
    public String loginm(Principal principal, Model model, HttpServletRequest request, @RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {

        HttpSession session = request.getSession();
        session.setAttribute("username", username);

        User user = userService.findByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            // User login with username and password
            model.addAttribute("user", "connect");
            model.addAttribute("name", user.getUsername());
        } else if (principal instanceof OAuth2AuthenticationToken) {
            // User login with OAuth2
            OAuth2AuthenticationToken authenticationToken = (OAuth2AuthenticationToken) principal;
            OAuth2User oauth2User = authenticationToken.getPrincipal();

            String firstName = oauth2User.getAttribute("given_name");
            String lastName = oauth2User.getAttribute("family_name");
            String email = oauth2User.getAttribute("email");

            // Utilisation des valeurs récupérées
            model.addAttribute("user", "connect");
            model.addAttribute("name", firstName + " " + lastName);
        } else {
            model.addAttribute("user", "noconnect");
            model.addAttribute("name", "username");
        }

        return "redirect:/";
    }


    @GetMapping("/jouer")
    public ModelAndView jouer(HttpServletRequest request) {

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        ModelAndView modelAndView = new ModelAndView("jouer");
        modelAndView.addObject("user", "connect");
        modelAndView.addObject("name", username);

        return modelAndView;

    }

    @PostMapping("/signup")
    public ModelAndView signup(HttpServletRequest request,
                               @RequestParam("name") String name,
                               @RequestParam("username") String username,
                               @RequestParam("password") String password) {

        try {
            User newUser = userService.signup(name, username, password);

            HttpSession session = request.getSession();
            session.setAttribute("username", username);

            User user = userService.findByUsername(username);
            String redirectUrl = "/?user=" + "noconnect"+"&username=";
            if (user != null) {
                redirectUrl = "/?user=" + "connect"+"&username="+username;
            }

            return new ModelAndView("redirect:" + redirectUrl);
        } catch (IllegalArgumentException e) {
            ModelAndView modelAndView = new ModelAndView("index");
            modelAndView.addObject("error", e.getMessage());
            return modelAndView;
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> userList = userService.getAllUsers();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("/number_validation")
    public ModelAndView number_validation(HttpServletRequest request) {

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        ModelAndView modelAndView = new ModelAndView("number_validation");
        modelAndView.addObject("user", "connect");
        modelAndView.addObject("name", username);

        return modelAndView;

    }
    @GetMapping("/number")
    public ModelAndView number(HttpServletRequest request) {

        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView("number");

        String param = request.getParameter("param").toString();
        if(!param.equals(codeValidation)){
           modelAndView = new ModelAndView("number_validation");
        }
        String username = (String) session.getAttribute("username");
        modelAndView.addObject("user", "connect");
        modelAndView.addObject("name", username);


        return modelAndView;

    }


}