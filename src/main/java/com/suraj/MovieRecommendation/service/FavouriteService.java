package com.suraj.MovieRecommendation.service;

import com.suraj.MovieRecommendation.entity.Favourite;
import com.suraj.MovieRecommendation.entity.Movie;
import com.suraj.MovieRecommendation.repository.FavouriteRepository;
import com.suraj.MovieRecommendation.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavouriteService {

    @Autowired
    private MovieService movieService;

    @Autowired
    private FavouriteRepository favouriteRepository;

    @Autowired
    private MovieRepository movieRepository;

//    POST METHODS

    public Favourite addFavourite(Favourite favourite){
        return favouriteRepository.save(favourite);
    }

//    GET METHODS

    public List<Movie> getAllFavouriteMovie(Long favouriteId){
        Optional<Favourite> favouriteDetail = favouriteRepository.findById(favouriteId);
        if (favouriteDetail.isPresent()){
            Favourite favourite = favouriteDetail.get();
            return favourite.getFavouriteMovie();
        }
        return null;
    }

//    DELETE METHODS

    public Boolean deleteFavouriteById(Long favouriteId){
        try {
            favouriteRepository.deleteById(favouriteId);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

//    UPDATE METHODS

    public Favourite addMovieToFavourite(Long favouriteId, Long movieId){
        Optional<Favourite> favouriteDetails = favouriteRepository.findById(favouriteId);
        if (favouriteDetails.isPresent()){
            Optional<Movie> movieDetails = movieService.getMovieById(movieId);
            if (movieDetails.isPresent()) {
                Favourite favourite = favouriteDetails.get();
                Movie movie = movieDetails.get();
                movie.getFavourite().add(favourite);
                favourite.getFavouriteMovie().add(movie);
                movieRepository.save(movie);
                return favouriteRepository.save(favourite);
            }
        }
        throw new RuntimeException("Favourite List not found!");
    }
}
