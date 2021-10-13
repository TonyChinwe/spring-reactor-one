package com.phisoft.springreactorone.router;

import com.phisoft.springreactorone.handler.SampleHandlerFunction1;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class RouterFunctionConfig {

    @Bean
    public RouterFunction<ServerResponse> route(SampleHandlerFunction1 handlerFunction1){

        return RouterFunctions.route(GET("func/flux").and(accept(MediaType.APPLICATION_JSON)),handlerFunction1::flux)
                .andRoute(GET("func/mono").and(accept(MediaType.APPLICATION_JSON)),handlerFunction1::mono);
    }
}
