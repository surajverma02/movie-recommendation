package com.suraj.MovieRecommendation.service;

import com.suraj.MovieRecommendation.entity.Movie;
import com.suraj.MovieRecommendation.entity.Wishlist;
import com.suraj.MovieRecommendation.repository.MovieRepository;
import com.suraj.MovieRecommendation.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishlistService {

    @Autowired
    private MovieService movieService;

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private MovieRepository movieRepository;

//    POST METHODS

    public Wishlist addWishlist(Wishlist wishlist){
        return wishlistRepository.save(wishlist);
    }

//    GET METHODS

    public List<Movie> getAllWishlistMovie(Long wishlistId){
        Optional<Wishlist> wishlistDetail = wishlistRepository.findById(wishlistId);
        if (wishlistDetail.isPresent()){
            Wishlist wishlist = wishlistDetail.get();
            return wishlist.getWishlistMovie();
        }
        return null;
    }

//    DELETE METHODS

    public Boolean deleteWishlistById(Long wishlistId){
        try {
            wishlistRepository.deleteById(wishlistId);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

//    UPDATE METHODS

    public Wishlist addMovieToWishlist(Long wishlistId, Long movieId){
        Optional<Wishlist> wishlistDetails = wishlistRepository.findById(wishlistId);
        if (wishlistDetails.isPresent()){
            Optional<Movie> movieDetails = movieService.getMovieById(movieId);
            if (movieDetails.isPresent()) {
                Wishlist wishlist = wishlistDetails.get();
                Movie movie = movieDetails.get();
                movie.getWishlist().add(wishlist);
                wishlist.getWishlistMovie().add(movie);
                movieRepository.save(movie);
                return wishlistRepository.save(wishlist);
            }
        }
        throw new RuntimeException("Wishlist not found!");
    }
}
