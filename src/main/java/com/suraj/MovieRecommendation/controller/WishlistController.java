package com.suraj.MovieRecommendation.controller;

import com.suraj.MovieRecommendation.entity.Movie;
import com.suraj.MovieRecommendation.entity.Wishlist;
import com.suraj.MovieRecommendation.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @PostMapping("/add")
    public ResponseEntity<Wishlist> addWishlist(@RequestBody Wishlist wishlist) {
        try {
            return new ResponseEntity<>(wishlistService.addWishlist(wishlist), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all/id/{wishlistId}")
    public ResponseEntity<List<Movie>> getAllWishlistMovie(@PathVariable Long wishlistId) {
        try {
            return new ResponseEntity<>(wishlistService.getAllWishlistMovie(wishlistId), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteWishlist(@PathVariable Long id) {
        try {
            if (wishlistService.deleteWishlistById(id)) {
                return new ResponseEntity<>("Wishlist deleted successfully", HttpStatus.OK);
            }
            throw new RuntimeException("No wishlist found!");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{wishlistId}/{movieId}")
    public ResponseEntity<Wishlist> addMovieToWishlist(@PathVariable Long wishlistId, @PathVariable Long movieId) {
        try {
            return new ResponseEntity<>(wishlistService.addMovieToWishlist(wishlistId, movieId), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
