package com.suraj.MovieRecommendation.service;

import com.suraj.MovieRecommendation.entity.User;
import com.suraj.MovieRecommendation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


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
}
