package com.suraj.MovieRecommendation.service;

import com.suraj.MovieRecommendation.entity.Movie;
import com.suraj.MovieRecommendation.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

//    POST METHODS

    public Movie createMovie(Movie movie) {
        movie.setGenre(movie.getGenre().toLowerCase());
        return movieRepository.save(movie);
    }

//    GET METHODS

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> getMovieById(Long id) {
        return movieRepository.findById(id);
    }

    public List<Movie> getMovieByGenre(String genre) {
        return movieRepository.findByGenre(genre);
    }

//    DELETE METHODS

    public Boolean deleteMovie(Long movieId) {
        try {
            movieRepository.deleteById(movieId);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

//    UPDATE METHODS

    public Movie updateMovie(Movie movie, Long movieId) {
        Optional<Movie> oldMovie = movieRepository.findById(movieId);
        if (oldMovie.isPresent()) {
            Movie updatedMovie = oldMovie.get();
            updatedMovie.setMovieName(movie.getMovieName());
            updatedMovie.setGenre(movie.getGenre().toLowerCase());
            updatedMovie.setYear(movie.getYear());
            return movieRepository.save(updatedMovie);
        }
        throw new RuntimeException("Producer not found!");
    }
}
