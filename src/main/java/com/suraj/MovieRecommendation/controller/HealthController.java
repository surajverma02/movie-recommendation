package com.suraj.MovieRecommendation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/admin/health-check")
    public ResponseEntity<String> healthCheckAdmin(){
        try {
            return new ResponseEntity<>("Welcome, Admin", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user/health-check")
    public ResponseEntity<String> healthCheckUser(){
        try {
            return new ResponseEntity<>("Welcome, User", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
