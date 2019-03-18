package hello;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
        //List<String> messages = Arrays.asList("Hello1");
        return Flux.fromIterable(messages)
                .flatMap(this::getPrime)
                //.delayElements(Duration.ofSeconds(2))
                ;
    }

    public Flux<Mono<String>> helloMultipleAnother() {
        List<String> messages = Arrays.asList("Hello1", "Hello2", "Hello3", "Hello4", "Hello5", "Hello6");
        //List<String> messages = Arrays.asList("Hello1");
        return Flux.fromIterable(messages)
                .flatMap(this::getPrimeAnother)
                //.delayElements(Duration.ofSeconds(2))
                ;
    }

    private static String MYSTRING = "JJ";

    private Mono<String> getPrime(String in) {
        return Mono.delay(Duration.ofSeconds(3)).map(lng -> lng.toString() + "JJ").map(lng -> in + lng);
    }

    private Mono<Mono<String>> getPrimeAnother(String in) {
        Mono<String> delayMonoString = Mono.delay(Duration.ofSeconds(3)).map(lng -> lng.toString() + in);
        return Mono.just(delayMonoString);
    }

    private boolean isPrime(int n, int i) {
        // Base cases
        if (n <= 2)
            return (n == 2) ? true : false;
        if (n % i == 0)
            return false;
        if (i * i > n)
            return true;

        // Check for next divisor
        return isPrime(n, i + 1);
    }

    private int getRandNumber() {
        Random rand = new Random(new Date().getTime());
        return rand.nextInt();
    }

}
