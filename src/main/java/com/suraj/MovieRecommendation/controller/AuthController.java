package com.suraj.MovieRecommendation.controller;

import com.suraj.MovieRecommendation.entity.User;
import com.suraj.MovieRecommendation.service.UserService;
import com.suraj.MovieRecommendation.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        try {
            User newUser = userService.registerUser(user);
            if (newUser != null) {
                return new ResponseEntity<>("User created successfully", HttpStatus.OK);
            }
            throw new RuntimeException("User not created!");
        }catch (Exception e){
            System.out.println("Error :: " + e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String jwt = jwtUtil.generateToken(userDetails.getUsername(), user.getRole());
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        }catch (Exception e){
            String error = "loginUser :: " + e;
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

    }
}
