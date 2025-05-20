package com.microservice.pattern.sec1.dto;

import lombok.*;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class ProductAggregate {

    private Integer id;
    private String category;
    private String description;
    private Price price;
    private List<Review> reviews;
}
