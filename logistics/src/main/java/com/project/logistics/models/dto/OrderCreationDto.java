package com.project.logistics.models.dto;

import com.project.logistics.models.Routes;
import com.project.logistics.models.Users;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class OrderCreationDto {

    private String cityFrom;
    private String cityTo;
    private Integer cargoWeight;
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Routes route;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;
}
