package com.microservice.pattern.sec2.service;

import com.microservice.pattern.sec2.client.DeltaClient;
import com.microservice.pattern.sec2.client.FrontierClient;
import com.microservice.pattern.sec2.client.JetBlueClient;
import com.microservice.pattern.sec2.dto.FlightResutl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Service
public class FlightSearchService {

    @Autowired
    private JetBlueClient jetBlueClient;

    @Autowired
    private DeltaClient deltaClient;

    @Autowired
    private FrontierClient frontierClient;

    public Flux<FlightResutl> getFlights(String from, String to) {
        return Flux.merge(
                deltaClient.getFlights(from, to),
                frontierClient.getFlights(from, to),
                jetBlueClient.getFlights(from, to)
        ).take(Duration.ofSeconds(3));
    }



}
