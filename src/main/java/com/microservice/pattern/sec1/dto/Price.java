package com.microservice.pattern.sec1.dto;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
public class Price {
    private Integer listPrice;
    private Integer discount;
    private Integer discountedPrice;
    private Integer amountSaved;
    private LocalDate endDate;
}
