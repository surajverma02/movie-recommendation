package com.suraj.MovieRecommendation.service;

import com.suraj.MovieRecommendation.entity.Actor;
import com.suraj.MovieRecommendation.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ActorService {

    @Autowired
    private ActorRepository actorRepository;


//    POST METHODS

    public Actor addActor(Actor actor){
        return actorRepository.save(actor);
    }

//    GET METHODS

    public List<Actor> getAllActors(){
        return actorRepository.findAll();
    }

    public Optional<Actor> getActorByActorName(String actorName) {
        return actorRepository.findByActorName(actorName);
    }

    public Optional<Actor> getActorById(Long actorId){
        return actorRepository.findById(actorId);
    }

//    DELETE METHODS

    public Boolean deleteActorById(Long actorId){
        try {
            actorRepository.deleteById(actorId);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

//    UPDATE METHODS

    public Actor updateActor(Actor actor, Long actorId){
        Optional<Actor> oldActor = actorRepository.findById(actorId);
        if (oldActor.isPresent()){
            Actor customActor = oldActor.get();
            customActor.setActorName(actor.getActorName());
            customActor.setActorRole(actor.getActorRole());
            return actorRepository.save(customActor);
        }
        throw new RuntimeException("Producer not found!");
    }
}
