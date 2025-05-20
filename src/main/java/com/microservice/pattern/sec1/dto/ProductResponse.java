package com.microservice.pattern.sec1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor(staticName = "create")
public class ProductResponse {

    // This class is used to represent the response of a product
    private Integer id;
    private String category;
    private String description;
    private Integer price;
}
