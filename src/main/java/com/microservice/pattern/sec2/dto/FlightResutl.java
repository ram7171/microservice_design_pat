package com.microservice.pattern.sec2.dto;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
public class FlightResutl {
    private String airline;
    private String from;
    private String to;
    private Double price;
    private LocalDate date;
}
