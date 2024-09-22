package com.suraj.MovieRecommendation.controller;

import com.suraj.MovieRecommendation.entity.User;
import com.suraj.MovieRecommendation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/add-favourite/{userId}/{favouriteId}")
    public ResponseEntity<User> AddFavouriteToUes(@PathVariable Long userId, @PathVariable Long favouriteId){
        try {
            return new ResponseEntity<>(userService.addFavouriteToUser(userId, favouriteId), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/add-wishlist/{userId}/{wishlistId}")
    public ResponseEntity<User> addWishlistToUser(@PathVariable Long userId, @PathVariable Long wishlistId){
        try {
            return new ResponseEntity<>(userService.addWishlistToUser(userId, wishlistId), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
