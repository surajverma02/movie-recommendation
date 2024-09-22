package com.suraj.MovieRecommendation.service;

import com.suraj.MovieRecommendation.entity.Favourite;
import com.suraj.MovieRecommendation.entity.Movie;
import com.suraj.MovieRecommendation.entity.Wishlist;
import com.suraj.MovieRecommendation.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private MovieService movieService;

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
        Optional<Wishlist> oldWishlist = wishlistRepository.findById(wishlistId);
        if (oldWishlist.isPresent()){
            Optional<Movie> movie = movieService.getMovieById(movieId);
            if (movie.isPresent()) {
                Wishlist customWishlist = oldWishlist.get();
                customWishlist.getWishlistMovie().add(movie.get());
                return wishlistRepository.save(customWishlist);
            }
        }
        throw new RuntimeException("Wishlist not found!");
    }
}
