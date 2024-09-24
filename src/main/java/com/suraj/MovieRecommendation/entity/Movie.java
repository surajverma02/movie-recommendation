package com.suraj.MovieRecommendation.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;
    private String movieName;
    private String genre;
    private Integer year;

    @ManyToOne
    @JoinColumn(name = "producer_id")
    @JsonBackReference
    private Producer producer;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "movie_actor",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    @JsonBackReference
    private List<Actor> actors = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "movie_favourite",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "favourite_id")
    )
    @JsonBackReference
    private List<Favourite> favourite = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "movie_wishlist",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "wishlist_id")
    )
    @JsonBackReference
    private List<Wishlist> wishlist = new ArrayList<>();
}
