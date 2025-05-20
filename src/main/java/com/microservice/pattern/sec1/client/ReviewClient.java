package com.microservice.pattern.sec1.client;

import com.microservice.pattern.sec1.dto.Review;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;


@Service
public class ReviewClient {
    private static final Logger logger = LoggerFactory.getLogger(ReviewClient.class);
    private final WebClient client;

    public ReviewClient(@Value("${sec01.review.service}") String reviewServiceUrl) {
        this.client = WebClient.builder()
                .baseUrl(reviewServiceUrl)
                .build();
    }

    public Mono<List<Review>> getReviewsByProductId(Integer productId) {

        return client.get()
                .uri("{id}", productId)
                .retrieve()
                .bodyToFlux(Review.class)
                .collectList()
                .doOnNext(t -> logger.info("Called review service URL: {}/{}", client.get(), productId))
                .onErrorReturn(Collections.emptyList()); // this code is added to handle the error and return an empty list for resilience
    }
}