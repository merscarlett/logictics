package com.project.logistics.models.dto;

import lombok.Data;

@Data
public class OrderDto {

    private Long id;
    private String cityFrom;
    private String cityTo;
    private Double cargoWeight;
    private Double price;
}
