package com.suraj.MovieRecommendation.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "producer")
public class Producer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long producerId;
    private String producerName;
    private String productionCompany;

    @OneToMany(mappedBy = "producer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Movie> movies = new ArrayList<>();
}
