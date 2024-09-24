package com.suraj.MovieRecommendation.service;

import com.suraj.MovieRecommendation.entity.*;
import com.suraj.MovieRecommendation.repository.FavouriteRepository;
import com.suraj.MovieRecommendation.repository.UserRepository;
import com.suraj.MovieRecommendation.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FavouriteRepository favouriteRepository;

    @Autowired
    private WishlistRepository wishlistRepository;


//    POST METHODS

    public User registerUser(User user){
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(user.getRoles().isEmpty()) {
            user.setRoles(Arrays.asList("USER"));
        }
        return userRepository.save(user);
    }

    public User registerAdmin(User user){
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(user.getRoles().isEmpty()) {
            user.setRoles(Arrays.asList("USER", "ADMIN"));
        }
        return userRepository.save(user);
    }

//    GET METHODS

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> getUserById(Long userId){
        return userRepository.findById(userId);
    }

//    DELETE METHODS

    public Boolean deleteUserByUsername(String username){
        try {
            userRepository.deleteByUsername(username);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Boolean deleteUserById(Long userId){
        try {
            userRepository.deleteById(userId);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

//    UPDATE METHODS

    public User updateUser(User user){
        Optional<User> oldUser = userRepository.findByUsername(user.getUsername());
        if (oldUser.isPresent()){
            User customUser = oldUser.get();
            customUser.setUsername(user.getUsername());
            customUser.setPassword(user.getPassword());
            //oldUser.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(customUser);
        }
        throw new RuntimeException("User not found!");
    }

    public User updateUserAsAdmin(Long userId){
        Optional<User> oldUser = userRepository.findById(userId);
        if (oldUser.isPresent()){
            User customUser = oldUser.get();
            customUser.getRoles().add("ADMIN");
            return userRepository.save(customUser);
        }
        throw new RuntimeException("User not found!");
    }

    public User addFavouriteToUser(Long userId, Long favouriteId){
        Optional<Favourite> favouriteDetails = favouriteRepository.findById(favouriteId);
        if (favouriteDetails.isPresent()) {
            Optional<User> userDetail = userRepository.findById(userId);
            if (userDetail.isPresent()){
                    User user = userDetail.get();
                    Favourite favourite = favouriteDetails.get();
                    user.setFavourite(favourite);
                    favourite.setUser(user);
                    favouriteRepository.save(favourite);
                    return userRepository.save(user);
            }
        }
        throw new RuntimeException("User not found!");
    }

    public User addWishlistToUser(Long userId, Long wishlistId){
        Optional<User> userDetail = userRepository.findById(userId);
        if (userDetail.isPresent()){
            Optional<Wishlist> wishlistDetails = wishlistRepository.findById(wishlistId);
            if (wishlistDetails.isPresent()) {
                User user = userDetail.get();
                Wishlist wishlist = wishlistDetails.get();
                wishlist.setUser(user);
                user.setWishlist(wishlist);
                wishlistRepository.save(wishlist);
                return userRepository.save(user);
            }
        }
        throw new RuntimeException("User not found!");
    }
}
