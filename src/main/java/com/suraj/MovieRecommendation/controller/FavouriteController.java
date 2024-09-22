package com.suraj.MovieRecommendation.controller;

import com.suraj.MovieRecommendation.entity.Favourite;
import com.suraj.MovieRecommendation.entity.Movie;
import com.suraj.MovieRecommendation.service.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favourite")
public class FavouriteController {

    @Autowired
    private FavouriteService favouriteService;

    @PostMapping("/add")
    public ResponseEntity<Favourite> addFavourite(@RequestBody Favourite favourite) {
        try {
            return new ResponseEntity<>(favouriteService.addFavourite(favourite), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all/id/{favouriteId}")
    public ResponseEntity<List<Movie>> getAllFavouriteMovie(@PathVariable Long favouriteId) {
        try {
            return new ResponseEntity<>(favouriteService.getAllFavouriteMovie(favouriteId), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFavourite(@PathVariable Long id) {
        try {
            if (favouriteService.deleteFavouriteById(id)) {
                return new ResponseEntity<>("FavouriteList deleted successfully", HttpStatus.OK);
            }
            throw new RuntimeException("No favourite list found!");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{favouriteId}/{movieId}")
    public ResponseEntity<Favourite> addMovieToFavourite(@PathVariable Long favouriteId, @PathVariable Long movieId) {
        try {
            return new ResponseEntity<>(favouriteService.addMovieToFavourite(favouriteId, movieId), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
