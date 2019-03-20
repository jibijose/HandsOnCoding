package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Random;
import java.util.stream.Stream;

@RestController
public class TemperatureController {

    Logger logger = LoggerFactory.getLogger(TemperatureController.class);

    @Autowired
    private GreetingHandler greetingHandler;

/*    @GetMapping(value = "/alok", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> getAlok() {
        return greetingHandler.helloMultiple();
    }*/

    @GetMapping(value = "/temperatures", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Integer> getTemperatures() {
        logger.debug("Starting temp endpoint");
        Random r = new Random();
        int low = 0;
        int high = 50;

        return Flux.fromStream(Stream.generate(() -> r.nextInt(high - low) + low)
                .map(s -> String.valueOf(s))
                .peek((msg) -> {
//                    logger.debug(msg);
                }))
                .map(s -> Integer.valueOf(s))
                .delayElements(Duration.ofMillis(1));
    }

    @GetMapping(value = "/temperature", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<String> getTemperature() {
        logger.debug("Starting temp endpoint");
        return Mono.just("Jibi Jose");
    }

}
