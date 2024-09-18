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

    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById( Long id) {
        Optional<Movie> movie = movieRepository.findById(id);
        return movie.orElse(null);
    }

    public Boolean deleteMovie(Long id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isPresent()) {
            movieRepository.delete(movie.get());
            return true;
        }
        return false;
    }

    public Movie updateMovie(Long id, Movie movieDetails) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isPresent()) {
            Movie updatedMovie = movie.get();
            updatedMovie.setMovieName(movieDetails.getMovieName());
            updatedMovie.setGenre(movieDetails.getGenre());
            updatedMovie.setYear(movieDetails.getYear());
            movieRepository.save(updatedMovie);
            return updatedMovie;
        }
        return null;
    }
}
