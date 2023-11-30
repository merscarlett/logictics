package com.project.logistics.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Routes route;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    private String cityFrom;
    private String cityTo;
    private Double cargoWeight;
    private Double price;
}
