package com.suraj.MovieRecommendation.controller;

import com.suraj.MovieRecommendation.entity.Actor;
import com.suraj.MovieRecommendation.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/actor")
public class ActorController {

    @Autowired
    private ActorService actorService;

    @PostMapping("/add")
    public ResponseEntity<Actor> addActor(@RequestBody Actor actor) {
        try {
            return new ResponseEntity<>(actorService.addActor(actor), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Actor>> getAllActors() {
        try {
            return new ResponseEntity<>(actorService.getAllActors(), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Actor> getActorById(@PathVariable Long id) {
        try {
            Optional<Actor> actor = actorService.getActorById(id);
            if (actor.isPresent()) {
                return new ResponseEntity<>(actor.get(), HttpStatus.OK);
            }
            throw new RuntimeException("No actor found!");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Actor> getActorByName(@PathVariable String name) {
        try {
            Optional<Actor> actor = actorService.getActorByActorName(name);
            if (actor.isPresent()) {
                return new ResponseEntity<>(actor.get(), HttpStatus.OK);
            }
            throw new RuntimeException("No actor found!");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteActor(@PathVariable Long id) {
        try {
            if (actorService.deleteActorById(id)) {
                return new ResponseEntity<>("Actor deleted successfully", HttpStatus.OK);
            }
            throw new RuntimeException("No actor found!");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Actor> updateActor(@RequestBody Actor actor, @PathVariable Long id) {
        try {
            return new ResponseEntity<>(actorService.updateActor(actor, id), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
