package com.microservice.pattern.sec2.client;

import com.microservice.pattern.sec2.dto.FlightResutl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class JetBlueClient {
    private final WebClient webClient;

    public JetBlueClient(@Value("${sec02.jetblue.service}") String baseUrl) {
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
                .doOnNext(flight -> { System.out.println("Delta flight: " + flight);
                this. normalizeFlight(flight, from, to);})
                .onErrorResume(ex-> Mono.empty());
    }

    private void normalizeFlight(FlightResutl result, String from, String to) {
        // Normalize the flight data if necessary
        // This method can be implemented based on specific requirements
        result.setFrom(from);
        result.setTo(to);
        result.setAirline("JetBlue");
    }
}
