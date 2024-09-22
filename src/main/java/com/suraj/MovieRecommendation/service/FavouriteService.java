package com.suraj.MovieRecommendation.service;

import com.suraj.MovieRecommendation.entity.Favourite;
import com.suraj.MovieRecommendation.entity.Movie;
import com.suraj.MovieRecommendation.repository.FavouriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavouriteService {

    @Autowired
    private FavouriteRepository favouriteRepository;

    @Autowired
    private MovieService movieService;

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
        Optional<Favourite> oldFavourite = favouriteRepository.findById(favouriteId);
        if (oldFavourite.isPresent()){
            Optional<Movie> movie = movieService.getMovieById(movieId);
            if (movie.isPresent()) {
                Favourite customFavourite = oldFavourite.get();
                customFavourite.getFavouriteMovie().add(movie.get());
                return favouriteRepository.save(customFavourite);
            }
        }
        throw new RuntimeException("FavouriteList not found!");
    }
}
