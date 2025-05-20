package com.microservice.pattern.sec1.client;

import com.microservice.pattern.sec1.dto.PromotionResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
public class PromotionClient {
    private final WebClient client;

    public PromotionClient(@Value("${sec01.promotion.service}") String promotionServiceUrl) {
        this.client = WebClient.builder()
                .baseUrl(promotionServiceUrl)
                .build();
    }

    public Mono<PromotionResponse> getPromotionById(Integer id) {
        return client.get()
                .uri("{id}", id)
                .retrieve()
                .bodyToMono(PromotionResponse.class)
                .onErrorResume(ex -> {
                    // Handle the error and return a default value or an empty Mono
                    return Mono.just(PromotionResponse.create(-1, "no promotion", 0, LocalDate.now()));
                });
    }
}
