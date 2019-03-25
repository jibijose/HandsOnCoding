package pubsub;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.startsWith;

@RunWith(SpringRunner.class)
public class PubOnSubOnTest {

    @Test
    public void reactorIsSingleThreadedByDefault() {
        Flux<Integer> flux = Flux.range(0, 1000);
        assertThread(flux, "main").blockLast(Duration.ofSeconds(1));
    }

    @Test
    public void delayingElementsIntroducesThreads() {
        Flux<Integer> flux = Flux.range(0, 1000).delayElements(Duration.ofMillis(1));
        assertThread(flux, "parallel-").blockLast(Duration.ofSeconds(3));
    }

    @Test
    public void publishOn() {
        Flux<Integer> flux = Flux.range(0, 1000).publishOn(Schedulers.newSingle("newThread"));
        assertThread(flux, "newThread").blockLast(Duration.ofSeconds(1));
    }

    @Test
    public void subscribeOn() {
        Flux<Integer> flux = Flux.range(0, 1000).subscribeOn(Schedulers.newSingle("newThread"));
        assertThread(flux, "newThread").blockLast(Duration.ofSeconds(1));
    }

    @Test
    public void publishOnTwice() {
        Flux<Integer> flux = Flux.range(0, 1000);
        Flux<Integer> fluxOnOne = assertThread(flux.publishOn(Schedulers.newSingle("one")), "one");
        Flux<Integer> fluxOnOneOnTwo = assertThread(fluxOnOne.publishOn(Schedulers.newSingle("two")), "two");
        fluxOnOneOnTwo.blockLast(Duration.ofSeconds(1));
    }

    @Test
    public void subscribeOnTwice() {
        Flux<Integer> flux = Flux.range(0, 1000);
        Flux<Integer> fluxOnOne = assertThread(flux.subscribeOn(Schedulers.newSingle("one")), "one");
        Flux<Integer> fluxOnOneOnTwo = assertThread(fluxOnOne.subscribeOn(Schedulers.newSingle("two")), "one");
        fluxOnOneOnTwo.blockLast(Duration.ofSeconds(1));
    }

    static Flux<String> addThread(Flux<?> flux) {
        return flux.map(e -> e + " " + Thread.currentThread());
    }

    static <T> Flux<T> assertThread(Flux<T> flux, String name) {
        return flux.doOnNext(
                e -> assertThat(Thread.currentThread().getName(),
                        startsWith(name))
        );
    }
}
