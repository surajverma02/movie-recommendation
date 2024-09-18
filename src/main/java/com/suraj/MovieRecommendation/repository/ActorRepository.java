package com.suraj.MovieRecommendation.repository;

import com.suraj.MovieRecommendation.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {
}
