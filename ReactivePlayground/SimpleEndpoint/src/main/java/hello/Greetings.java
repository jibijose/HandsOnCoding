package hello;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.Stream;

@RestController
public class Greetings {

    @Autowired
    private GreetingHandler greetingHandler;

    @GetMapping(value = "/greetings/ssepub", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Publisher<String> sseGreetingsPub() {

      Flux<String> myFlux = Flux.interval(Duration.ofSeconds(1)).map(lg -> Long.toString(lg));

        Flux<String> delayElements = Flux
                .<String>generate(sink -> sink.next("Hello @" + Instant.now().toString()))
                .log()
                .delayElements(Duration.ofSeconds(1));
                //.delayElements(Duration.ofHours(1));
        return delayElements;
    }

    @GetMapping(value = "/greetings/sseflux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> sseGreetingsFlux() {
        Flux<String> greetingFlux = Flux.fromStream(Stream.generate(() -> "Hello @" + Instant.now().toString()));
        Flux<Long> durationFlux = Flux.interval(Duration.ofSeconds(1));
        return Flux.zip(greetingFlux, durationFlux).map(Tuple2::getT1);
    }

    @GetMapping(value = "/hello", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> helloFlux() {
        return greetingHandler.helloMultiple();
    }

    @GetMapping(value = "/helloanother", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Mono<String>> helloFluxAnother() {
        return greetingHandler.helloMultipleAnother();
    }

}
