package com.suraj.MovieRecommendation.service;

import com.suraj.MovieRecommendation.entity.Producer;
import com.suraj.MovieRecommendation.repository.ProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ProducerService {

    @Autowired
    private ProducerRepository producerRepository;


//    POST METHODS

    public Producer addProducer(Producer producer){
        return producerRepository.save(producer);
    }

//    GET METHODS

    public List<Producer> getAllProducers(){
        return producerRepository.findAll();
    }

    public Optional<Producer> getProducerByUsername(String producerName) {
        return producerRepository.findByProducerName(producerName);
    }

    public Optional<Producer> getProducerById(Long producerId){
        return producerRepository.findById(producerId);
    }

//    DELETE METHODS

    public Boolean deleteProducerById(Long producerId){
        try {
            producerRepository.deleteById(producerId);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

//    UPDATE METHODS

    public Producer updateProducer(Producer producer, Long producerId){
        Optional<Producer> oldProducer = producerRepository.findById(producerId);
        if (oldProducer.isPresent()){
            Producer customProducer = oldProducer.get();
            customProducer.setProducerName(producer.getProducerName());
            customProducer.setProductionCompany(producer.getProductionCompany());
            return producerRepository.save(customProducer);
        }
        throw new RuntimeException("Producer not found!");
    }
}
