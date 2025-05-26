package com.microservice.pattern.sec2.controller;

import com.microservice.pattern.sec2.dto.FlightResutl;
import com.microservice.pattern.sec2.service.FlightSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("sec02")
public class FlightController {

    @Autowired
    private FlightSearchService service;

    @GetMapping(value = "/flights/{from}/{to}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<FlightResutl> getFlights(@PathVariable String from, @PathVariable String to) {
        return service.getFlights(from, to);

    }
}
