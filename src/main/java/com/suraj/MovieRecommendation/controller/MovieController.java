package com.suraj.MovieRecommendation.controller;

import com.suraj.MovieRecommendation.entity.Movie;
import com.suraj.MovieRecommendation.repository.MovieRepository;
import com.suraj.MovieRecommendation.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieService movieService;

    @PostMapping("/add")
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        try {
            Movie savedMovie = movieService.createMovie(movie);
            return new ResponseEntity<>(savedMovie, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Movie>> getAllMovies() {
        try {
            List<Movie> savedMovies = movieService.getAllMovies();
            if (savedMovies != null) {
                return new ResponseEntity<>(savedMovies, HttpStatus.OK);
            }
            throw new RuntimeException("No movie found!");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        try {
            Movie movie = movieService.getMovieById(id);
            if (movie != null) {
                return new ResponseEntity<>(movie, HttpStatus.OK);
            }
            throw new RuntimeException("No movie found!");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        try {
            if (movieService.deleteMovie(id)) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            throw new RuntimeException("No movie found!");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movieDetails) {
        try {
            Movie movie = movieService.updateMovie(id, movieDetails);
            if (movie != null) {
                return new ResponseEntity<>(movie, HttpStatus.OK);
            }
            throw new RuntimeException("No movie found!");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
