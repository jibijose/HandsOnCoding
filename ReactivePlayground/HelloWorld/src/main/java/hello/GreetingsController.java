package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.Set;

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

    @GetMapping(value = "/hellodb", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ReactiveUser> helloDbStreamAll() {
        LOGGER.info("Database fetch starting");
        Flux<ReactiveUser> reactiveUsers = reactiveUserRepository.findAll();
        LOGGER.info("Database fetch ending");
        return reactiveUsers;
    }


}
