package com.microservice.pattern.sec1.client;

import com.microservice.pattern.sec1.dto.ProductResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ProductClient {
    private final WebClient client;
    private static final Logger logger = LoggerFactory.getLogger(ProductClient.class);

    public ProductClient(@Value("${sec01.product.service}") String productServiceUrl) {
        this.client = WebClient.builder()
                .baseUrl(productServiceUrl)
                .build();
    }

    public Mono<ProductResponse> getProductById(Integer id) {
        return client.get()
                .uri("{id}", id)
                .retrieve()
                .bodyToMono(ProductResponse.class)
                .onErrorResume(ex -> Mono.empty())
                .onErrorResume(ex -> {
                    // Handle the error and return a default value or an empty Mono
                    return Mono.just(ProductResponse.create(-1, "no product", "no category", 0));
                });
    }
}
