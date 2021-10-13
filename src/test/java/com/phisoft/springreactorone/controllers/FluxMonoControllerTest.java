package com.phisoft.springreactorone.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@WebFluxTest
class FluxMonoControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    void getFlux_Test() {
   Flux<Integer>integerFlux= webTestClient.get().uri("/flux")
                          .accept(MediaType.APPLICATION_JSON)
                           .exchange()
                           .expectStatus().isOk()
                           .returnResult(Integer.class)
                           .getResponseBody();
             StepVerifier.create(integerFlux).
                                         expectSubscription()
                                         .expectNext(1)
                                          .expectNext(2)
                                           .expectNext(3)
                                            .expectNext(4)
                                             .verifyComplete();

    }

    @Test
    void ifinitStream_Test() {
        Flux<Long>longFlux= webTestClient.get().uri("/ifinit_stream")
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Long.class)
                .getResponseBody();
        StepVerifier.create(longFlux).
                expectSubscription()
                .expectNext(0l)
                .expectNext(1l)
                .expectNext(2l)
                .thenCancel()
                .verify();

    }


    @Test
    void testFlux_two(){
     webTestClient.get().uri("/flux")
                         .accept(MediaType.APPLICATION_JSON)
                          .exchange()
                           .expectStatus().isOk()
                            .expectHeader().contentType(MediaType.APPLICATION_JSON)
                             .expectBodyList(Integer.class)
                              .hasSize(4);
    }

    @Test
    void testFlux_three(){
        List<Integer> integerList= Arrays.asList(1,2,3,4);
    EntityExchangeResult<List<Integer>>entityExchangeResult=
           webTestClient.get().uri("/flux")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(Integer.class)
                .returnResult();
        Assertions.assertEquals(integerList,entityExchangeResult.getResponseBody());
    }


    @Test
    void testFlux_four(){
        List<Integer> integerList= Arrays.asList(1,2,3,4);
                webTestClient.get().uri("/flux")
                        .accept(MediaType.APPLICATION_JSON)
                        .exchange()
                        .expectStatus().isOk()
                        .expectHeader().contentType(MediaType.APPLICATION_JSON)
                        .expectBodyList(Integer.class)
                        .consumeWith((response)->{
                            Assertions.assertEquals(integerList,response.getResponseBody());
                        });

    }


    @Test
    void getMono() {
        Integer integer=new Integer(1);
        webTestClient.get().uri("/mono")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(Integer.class)
                .consumeWith((response)->{
                   Assertions.assertEquals(integer,response.getResponseBody());
                });
    }
}