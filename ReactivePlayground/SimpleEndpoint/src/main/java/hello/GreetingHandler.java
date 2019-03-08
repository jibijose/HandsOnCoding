package hello;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Component
public class GreetingHandler {
    public Mono<ServerResponse> hello(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(BodyInserters.fromObject("Hello Spring"));
    }

    public Mono<ServerResponse> helloFlux(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(helloMultiple(), String.class);
    }

    public Flux<String> helloMultiple() {
        List<String> messages = Arrays.asList("Hello1", "Hello2", "Hello3", "Hello4", "Hello5", "Hello6");
        return Flux.fromIterable(messages);


        /*return Flux.create(fluxSink -> {
            int numOfTimes = 0;
            while (numOfTimes <= 10) {
                numOfTimes++;
                fluxSink.next("Hello, Spring " + numOfTimes);
*//*                try {
                    Thread.sleep(100);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }*//*
            }
        });*/
    }
}
