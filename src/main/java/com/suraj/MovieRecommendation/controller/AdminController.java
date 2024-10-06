package com.suraj.MovieRecommendation.controller;

import com.suraj.MovieRecommendation.entity.User;
import com.suraj.MovieRecommendation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        try {
            Optional<User> user = userService.getUserById(id);
            if (user.isPresent()) {
                return new ResponseEntity<>(user.get(), HttpStatus.OK);
            }
            throw new RuntimeException("No actor found!");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<User> getUserByName(@PathVariable String name) {
        try {
            Optional<User> user = userService.findByUsername(name);
            if (user.isPresent()) {
                return new ResponseEntity<>(user.get(), HttpStatus.OK);
            }
            throw new RuntimeException("No actor found!");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete-id/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        try {
            if (userService.deleteUserById(id)) {
                return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
            }
            throw new RuntimeException("No user found!");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete-name/{name}")
    public ResponseEntity<String> deleteUserByName(@PathVariable String name) {
        try {
            if (userService.deleteUserByUsername(name)) {
                return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
            }
            throw new RuntimeException("No user found!");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update-user/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        try {
            return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/update-admin/{id}")
    public ResponseEntity<User> updateUserAsAdmin(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(userService.updateUserAsAdmin(id), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
