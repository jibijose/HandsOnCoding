package hello.controller;

import com.couchbase.client.java.document.RawJsonDocument;
import hello.common.ReactorUtil;
import hello.db.CouchbaseConnector;
import hello.repository.ReactiveUserRepository;
import hello.value.ReactiveUser;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class GreetingsController {

    @Autowired
    private ReactiveUserRepository reactiveUserRepository;

    @Autowired
    private CouchbaseConnector couchbaseConnector;

    @GetMapping(value = "/hellosecond/{seconds}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String helloSecond(@PathVariable(value = "seconds") String seconds) {
        log.info("Sleeping " + seconds + " seconds.");
        try {
            Thread.sleep(Integer.parseInt(seconds) * 1000);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        String returnValue = "Slept " + seconds + " seconds.";
        log.info(returnValue);
        return returnValue;
    }

    @GetMapping(value = "/hellodb/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<ReactiveUser> helloDbStreamById(@PathVariable(value = "id") String id) {
        log.info("Database fetch starting");
        Mono<ReactiveUser> reactiveUsers = reactiveUserRepository.findById(id);
        log.info("Database fetch ending");
        return reactiveUsers;
    }

    @GetMapping(value = "/hellodbstream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ReactiveUser> helloDbStreamAll() {
        log.info("Database fetch starting");

        Mono<ReactiveUser> flux1 = reactiveUserRepository.findById("user::1");
        Mono<ReactiveUser> flux1Delayed = Mono.delay(Duration.ofSeconds(2)).then(flux1);
        Mono<ReactiveUser> flux2 = reactiveUserRepository.findById("user::2");
        Mono<ReactiveUser> flux2Delayed = Mono.delay(Duration.ofSeconds(1)).then(flux2);

        Flux<ReactiveUser> reactiveUsersDelayed = Flux.merge(flux1Delayed, flux2Delayed);

        log.info("Database fetch ending");
        return reactiveUsersDelayed;
    }

    @GetMapping(value = "/hellodbasync", produces = MediaType.APPLICATION_JSON_VALUE)
    public DeferredResult<List<ReactiveUser>> helloDbAsyncAll() {
        log.info("Database fetch starting");

        Mono<ReactiveUser> flux1 = reactiveUserRepository.findById("user::1");
        Mono<ReactiveUser> flux1Delayed = Mono.delay(Duration.ofSeconds(2)).then(flux1);
        Mono<ReactiveUser> flux2 = reactiveUserRepository.findById("user::2");
        Mono<ReactiveUser> flux2Delayed = Mono.delay(Duration.ofSeconds(1)).then(flux2);

        Flux<ReactiveUser> reactiveUsersDelayed = Flux.merge(flux1Delayed, flux2Delayed);
        //DeferredResult<ReactiveUser> deferredResult = ReactorUtil.toDeferredResult(reactiveUsersDelayed);
        DeferredResult<List<ReactiveUser>> deferredResult = ReactorUtil.toDeferredResultList(reactiveUsersDelayed);

        log.info("Database fetch ending");
        return deferredResult;
    }

    @GetMapping(value = "/projectdb", produces = MediaType.APPLICATION_JSON_VALUE)
    public DeferredResult<RawJsonDocument> helloDbKey() {
        Mono<RawJsonDocument> monoDocument = couchbaseConnector.get("user::1");
        DeferredResult<RawJsonDocument> deferredResult = ReactorUtil.toDeferredResult(monoDocument);
        return deferredResult;
    }


}
