package com.microservice.pattern.sec2.client;

import com.microservice.pattern.sec2.dto.FlightResutl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DeltaClient {
    private final WebClient webClient;

    public DeltaClient(@Value("${sec02.delta.service}") String baseUrl) {
        this.webClient = WebClient.builder().baseUrl(baseUrl).build();
    }

    public Flux<FlightResutl> getFlights(String from, String to) {
        return webClient.get()
                .uri("{from}/{to}",from, to)
              /*  .uri(uriBuilder -> uriBuilder.path("/flights")
                        .queryParam("from", from)
                        .queryParam("to", to)
                        .build())*/
                .retrieve()
                .bodyToFlux(FlightResutl.class)
                .doOnNext(flight -> System.out.println("Delta flight: " + flight))
                .onErrorResume(ex-> Mono.empty());
    }
}
