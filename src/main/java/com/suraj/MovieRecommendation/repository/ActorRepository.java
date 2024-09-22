package com.suraj.MovieRecommendation.repository;

import com.suraj.MovieRecommendation.entity.Actor;
import com.suraj.MovieRecommendation.entity.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {
    Optional<Actor> findByActorName(String actorName);
}
