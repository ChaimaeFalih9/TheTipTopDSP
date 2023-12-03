package com.jeux.tiptop_app.api;

import com.jeux.tiptop_app.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.jeux.tiptop_app.service.UserService;
import java.util.List;

/**
 * @author kaoutarelmofatiche
 */

@RestController
public class UserApi {

    @Autowired
    private UserService userService;


    @GetMapping("/users")
    public ResponseEntity<List<User>> users() {
        List<User> userList = userService.getAllUsers();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @PostMapping("/creat_user")
    public User creat_user(@RequestParam("email") String email,
                               @RequestParam("username") String username,
                               @RequestParam("password") String password) {

        User newUser = userService.signup(email, username, password);

        return newUser;
    }

    @DeleteMapping("/delete_user")
    public String delete_user(@RequestParam("id") Long id) {
        String Reponse = userService.deleteUser(id);
        return Reponse;
    }

}
