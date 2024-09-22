package com.suraj.MovieRecommendation.controller;

import com.suraj.MovieRecommendation.entity.User;
import com.suraj.MovieRecommendation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        try {
            return new ResponseEntity<>(userService.registerUser(user), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/login")
    public ResponseEntity<String> loginUser() {
        return new ResponseEntity<>("Login check completed!", HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logoutUser() {
        return new ResponseEntity<>("Logout check completed!", HttpStatus.OK);
    }
}
