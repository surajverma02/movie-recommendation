package com.suraj.MovieRecommendation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<String> defaultPage(){
        try {
            return new ResponseEntity<>("Some user and admin : (user1, user1)(user2, user2)(admin1, admin1).", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("There is some issue in Application. Try to resolve project needs for work!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/home")
    public ResponseEntity<String> homePage(){
        try {
            return new ResponseEntity<>("Welcome to home!", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("There is some issue in Application.", HttpStatus.BAD_REQUEST);
        }
    }
}
