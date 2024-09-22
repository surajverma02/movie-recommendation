package com.suraj.MovieRecommendation.controller;

import com.suraj.MovieRecommendation.entity.Movie;
import com.suraj.MovieRecommendation.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/add")
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        try {
            return new ResponseEntity<>(movieService.createMovie(movie), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Movie>> getAllMovies() {
        try {
            return new ResponseEntity<>(movieService.getAllMovies(), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        try {
            Optional<Movie> movie = movieService.getMovieById(id);
            if (movie.isPresent()) {
                return new ResponseEntity<>(movie.get(), HttpStatus.OK);
            }
            throw new RuntimeException("No movie found!");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<Movie>> getMovieByName(@PathVariable String genre) {
        try {
            return new ResponseEntity<>(movieService.getMovieByGenre(genre), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable Long id) {
        try {
            if (movieService.deleteMovie(id)) {
                return new ResponseEntity<>("Movie deleted successfully", HttpStatus.OK);
            }
            throw new RuntimeException("No movie found!");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Movie> updateMovie(@RequestBody Movie movie, @PathVariable Long id) {
        try {
            return new ResponseEntity<>(movieService.updateMovie(movie, id), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/add-producer/{movieId}/{producerId}")
    public ResponseEntity<Movie> addProducerToMovie(@PathVariable Long movieId, @PathVariable Long producerId){
        try {
            return new ResponseEntity<>(movieService.addProducerToMovie(movieId, producerId), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/add-actor/{movieId}/{actorId}")
    public ResponseEntity<Movie> addActorToMovie(@PathVariable Long movieId, @PathVariable Long actorId){
        try {
            return new ResponseEntity<>(movieService.addActorToMovie(movieId, actorId), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
