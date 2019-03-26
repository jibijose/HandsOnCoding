package hello.controller;

import hello.value.ReactiveUser;
import hello.repository.ReactiveUserRepository;
import hello.common.ReactorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

@RestController
public class GreetingsController {

    private static Logger LOGGER = LoggerFactory.getLogger(GreetingsController.class);

    @Autowired
    private ReactiveUserRepository reactiveUserRepository;

    @GetMapping(value = "/hellosecond/{seconds}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String helloSecond(@PathVariable(value = "seconds") String seconds) {
        LOGGER.info("Sleeping " + seconds + " seconds.");
        try {
            Thread.sleep(Integer.parseInt(seconds) * 1000);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        String returnValue = "Slept " + seconds + " seconds.";
        LOGGER.info(returnValue);
        return returnValue;
    }

    @GetMapping(value = "/hellodb/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<ReactiveUser> helloDbStreamById(@PathVariable(value = "id") String id) {
        LOGGER.info("Database fetch starting");
        Mono<ReactiveUser> reactiveUsers = reactiveUserRepository.findById(id);
        LOGGER.info("Database fetch ending");
        return reactiveUsers;
    }

    @GetMapping(value = "/hellodbstream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ReactiveUser> helloDbStreamAll() {
        LOGGER.info("Database fetch starting");

        Mono<ReactiveUser> flux1 = reactiveUserRepository.findById("user::1");
        Mono<ReactiveUser> flux1Delayed = Mono.delay(Duration.ofSeconds(2)).then(flux1);
        Mono<ReactiveUser> flux2 = reactiveUserRepository.findById("user::2");
        Mono<ReactiveUser> flux2Delayed = Mono.delay(Duration.ofSeconds(1)).then(flux2);

        Flux<ReactiveUser> reactiveUsersDelayed = Flux.merge(flux1Delayed, flux2Delayed);

        LOGGER.info("Database fetch ending");
        return reactiveUsersDelayed;
    }

    @GetMapping(value = "/hellodbasync", produces = MediaType.APPLICATION_JSON_VALUE)
    public DeferredResult<List<ReactiveUser>> helloDbAsyncAll() {
        LOGGER.info("Database fetch starting");

        Mono<ReactiveUser> flux1 = reactiveUserRepository.findById("user::1");
        Mono<ReactiveUser> flux1Delayed = Mono.delay(Duration.ofSeconds(2)).then(flux1);
        Mono<ReactiveUser> flux2 = reactiveUserRepository.findById("user::2");
        Mono<ReactiveUser> flux2Delayed = Mono.delay(Duration.ofSeconds(1)).then(flux2);

        Flux<ReactiveUser> reactiveUsersDelayed = Flux.merge(flux1Delayed, flux2Delayed);
        //DeferredResult<ReactiveUser> deferredResult = ReactorUtil.toDeferredResult(reactiveUsersDelayed);
        DeferredResult<List<ReactiveUser>> deferredResult = ReactorUtil.toDeferredResultList(reactiveUsersDelayed);

        LOGGER.info("Database fetch ending");
        return deferredResult;
    }


}
