package pubsub;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import reactor.util.Logger;
import reactor.util.Loggers;

import java.time.Duration;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
public class PubSubThreadTest {

    private static Logger LOGGER = Loggers.getLogger(PubSubThreadTest.class);

    @Test
    public void mainThreadRun() {
        Flux<Integer> flux = Flux.range(0, 2);
        logFluxSubscriber(flux);
    }

    @Test
    public void parallelThreadRun() {
        Flux<Integer> flux = Flux.range(0, 2).delayElements(Duration.ofMillis(1));
        logFluxSubscriber(flux);
    }

    @Test
    public void beforeAfterPublishOnWithoutDelay() {
        Flux<Integer> flux = Flux.range(0, 2)
                // this is influenced by subscribeOn
                .doOnNext(s -> LOGGER.debug(" before publishOn using thread: "))
                .publishOn(Schedulers.elastic())
                // the rest is influenced by publishOn
                .doOnNext(s -> LOGGER.debug(" after publishOn using thread: "))
                .subscribeOn(Schedulers.single());
        logFluxSubscriber(flux);
    }

    @Test
    public void beforeAfterPublishOnWithDelay() {
        Flux<Integer> flux = Flux.range(0, 2)
                // this is influenced by subscribeOn
                .doOnNext(s -> LOGGER.debug(" before publishOn using thread: "))
                .publishOn(Schedulers.elastic())
                // influenced by publishOn
                .doOnNext(s -> LOGGER.debug(" after publishOn using thread: "))
                .delayElements(Duration.ofMillis(1))
                // influcend by the Schedulers.parallel() caused by the delayElements operator
                .subscribeOn(Schedulers.single());
        logFluxSubscriber(flux);
    }

    static void logFluxSubscriber(Flux<Integer> flux) {
        IntStream.range(1, 5).forEach(value -> flux.subscribe(integer -> LOGGER.debug("Integer=" + integer)));
    }
}
