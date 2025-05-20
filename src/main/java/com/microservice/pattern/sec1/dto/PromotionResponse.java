package com.microservice.pattern.sec1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
@AllArgsConstructor(staticName = "create")
@NoArgsConstructor
public class PromotionResponse {
    private Integer id;
    private String type;
    private Integer discount;
    private LocalDate endDate;
}
