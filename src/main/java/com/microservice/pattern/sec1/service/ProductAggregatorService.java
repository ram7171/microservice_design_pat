package com.microservice.pattern.sec1.service;

import com.microservice.pattern.sec1.client.ProductClient;
import com.microservice.pattern.sec1.client.PromotionClient;
import com.microservice.pattern.sec1.client.ReviewClient;
import com.microservice.pattern.sec1.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ProductAggregatorService {

    private Logger logger = LoggerFactory.getLogger(ProductAggregatorService.class);

    @Autowired
    private  ProductClient productClient;

    @Autowired
    private  PromotionClient promotionClient;

    @Autowired
    private  ReviewClient reviewClient;

    public Mono<ProductAggregate> aggregate(Integer id) {
        return Mono.zip(
                this.productClient.getProductById(id),
                this.promotionClient.getPromotionById(id),
                this.reviewClient.getReviewsByProductId(id)
        ).map(t -> toDto(t.getT1(), t.getT2(), t.getT3()));
    }

    private ProductAggregate toDto(ProductResponse productResponse, PromotionResponse promotionResponse, List<Review> reviews) {
        var price = new Price();
        var amountSaved = productResponse.getPrice() * promotionResponse.getDiscount() / 100;
        var discountPrice = productResponse.getPrice() - amountSaved;
        price.setListPrice(productResponse.getPrice());
        price.setAmountSaved(amountSaved);
        price.setDiscountedPrice(discountPrice);
        price.setDiscount(promotionResponse.getDiscount());
        price.setEndDate(promotionResponse.getEndDate());
        return ProductAggregate.create(productResponse.getId(), productResponse.getCategory(), productResponse.getDescription(), price, reviews);
    }
}
