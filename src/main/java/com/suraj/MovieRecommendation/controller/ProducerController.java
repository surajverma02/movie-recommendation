package com.suraj.MovieRecommendation.controller;

import com.suraj.MovieRecommendation.entity.Producer;
import com.suraj.MovieRecommendation.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/producer")
public class ProducerController {

    @Autowired
    private ProducerService producerService;

    @PostMapping("/add")
    public ResponseEntity<Producer> addProducer(@RequestBody Producer producer) {
        try {
            return new ResponseEntity<>(producerService.addProducer(producer), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Producer>> getAllProducers() {
        try {
            return new ResponseEntity<>(producerService.getAllProducers(), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Producer> getProducerById(@PathVariable Long id) {
        try {
            Optional<Producer> producer = producerService.getProducerById(id);
            if (producer.isPresent()) {
                return new ResponseEntity<>(producer.get(), HttpStatus.OK);
            }
            throw new RuntimeException("No producer found!");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Producer> getProducerByName(@PathVariable String name) {
        try {
            Optional<Producer> producer = producerService.getProducerByUsername(name);
            if (producer.isPresent()) {
                return new ResponseEntity<>(producer.get(), HttpStatus.OK);
            }
            throw new RuntimeException("No producer found!");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProducer(@PathVariable Long id) {
        try {
            if (producerService.deleteProducerById(id)) {
                return new ResponseEntity<>("Producer deleted successfully", HttpStatus.OK);
            }
            throw new RuntimeException("No producer found!");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Producer> updateProducer(@RequestBody Producer producer, @PathVariable Long id) {
        try {
            return new ResponseEntity<>(producerService.updateProducer(producer, id), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
