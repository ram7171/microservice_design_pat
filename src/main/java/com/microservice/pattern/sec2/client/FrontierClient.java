package com.microservice.pattern.sec2.client;

import com.microservice.pattern.sec2.dto.FlightResutl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FrontierClient {
    private final WebClient webClient;

    public FrontierClient(@Value("${sec02.frontier.service}") String baseUrl) {
        this.webClient = WebClient.builder().baseUrl(baseUrl).build();
    }

    public Flux<FlightResutl> getFlights(String from, String to) {
        return webClient.post()
                .bodyValue(FrontierRequest.create(from, to))
                .retrieve()
                .bodyToFlux(FlightResutl.class)
                .doOnNext(flight -> { System.out.println("Frontier flight: " + flight);})
                .onErrorResume(ex-> Mono.empty());
    }

    @Data
    @ToString
    @AllArgsConstructor(staticName = "create")
    private static class FrontierRequest {
        private String from;
        private String to;
    }

}
