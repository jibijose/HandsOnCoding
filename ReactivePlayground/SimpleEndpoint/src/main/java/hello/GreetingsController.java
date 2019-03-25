package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class GreetingsController {

    @Autowired
    private GreetingService greetingService;

    @GetMapping(value = "/hellostream/{maxPrimeCheckNum}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> helloStream(@PathVariable(value = "maxPrimeCheckNum") String maxPrimeCheckNum) {
        return greetingService.factorialResultStream(Integer.parseInt(maxPrimeCheckNum));
    }

    @GetMapping(value = "/hello/{maxPrimeCheckNum}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String helloOld(@PathVariable(value = "maxPrimeCheckNum") String maxPrimeCheckNum) throws Exception {
        return greetingService.factorialResults(Integer.parseInt(maxPrimeCheckNum));
    }


}
