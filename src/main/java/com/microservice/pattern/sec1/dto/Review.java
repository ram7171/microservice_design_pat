package com.microservice.pattern.sec1.dto;

import lombok.Data;

import lombok.ToString;

@Data
@ToString

public class Review {
    private Integer id;
    private String user;
    private Integer rating;
    private String comment;
}
