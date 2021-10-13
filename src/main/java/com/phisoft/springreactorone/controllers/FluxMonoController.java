package com.phisoft.springreactorone.controllers;

import com.phisoft.springreactorone.services.Incrementer;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@RestController
public class FluxMonoController {


    @GetMapping(value = "flux")
    public Flux<Integer> getFlux(){
        return Flux.just(1,2,3,4)
                .delayElements(Duration.ofSeconds(1))
                .log();
    }
    @GetMapping(value = "flux_stream",produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Integer> getFluxStream(){
        Incrementer incrementer=new Incrementer();
        Thread thread=new Thread(incrementer);
        thread.start();
        return Flux.fromIterable(incrementer.getNumList())
                .delayElements(Duration.ofSeconds(1))
                .log();
    }

    @GetMapping(value = "ifinit_stream",produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Long> ifinitStream(){
        return Flux.interval(Duration.ofSeconds(1))
                .log();
    }
    @GetMapping("mono")
    public Mono<Integer> getMono(){
        return Mono.just(1).log();
    }

}
