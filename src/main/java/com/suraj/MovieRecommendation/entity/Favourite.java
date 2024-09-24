package com.suraj.MovieRecommendation.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "favourite")
public class Favourite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long favouriteId;
    private String favouriteName;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(mappedBy = "favourite", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Movie> favouriteMovie = new ArrayList<>();
}
