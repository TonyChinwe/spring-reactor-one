package com.phisoft.springreactorone.handler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWebTestClient
class SampleHandlerFunction1Test {



    @Autowired
    WebTestClient webTestClient;

    @Test
    void test_Mono(){
        Mono<String> mono=Mono.just("tony");
        StepVerifier.create(mono)
                .expectNext("tony")
                .verifyComplete();
    }


    @Test
    void getFlux_Test_func() {
        Flux<Integer> integerFlux= webTestClient.get().uri("/func/flux")
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
                .verifyComplete();

    }

    @Test
    void getMono_func() {
        Integer integer=new Integer(1);
        webTestClient.get().uri("/func/mono")
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