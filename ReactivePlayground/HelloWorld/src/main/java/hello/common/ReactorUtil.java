package hello.common;

import hello.controller.GreetingsController;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.async.DeferredResult;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.ParallelFlux;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public class ReactorUtil {

    private static Logger LOGGER = LoggerFactory.getLogger(GreetingsController.class);

    public static <T> DeferredResult<T> toDeferredResult(final Publisher<T> publisher) {
        LOGGER.error("Getting deferredresult");
        DeferredResult<T> deferredResult = new DeferredResult<>();
        Disposable disposable = Flux.from(publisher).subscribeOn(Schedulers.parallel()).subscribe(
                // normal scenario where Mono returns a value
                t -> {
                    System.out.println("Getting result");
                    deferredResult.setErrorResult(t);
                },
                // error scenario where Mono returns an exception
                error -> {
                    System.out.println("Error");
                    deferredResult.setErrorResult(error);
                },
                // empty Mono (e.g., Mono<Void>, Mono.empty())
                () -> {
                    System.out.println("Getting empty");
                    if (!deferredResult.hasResult()) {
                        deferredResult.setResult(null);
                    }
                }
        );
        // cancel subscription on timeout
        deferredResult.onTimeout(disposable::dispose);
        return deferredResult;
    }

    public static <T> DeferredResult<List<T>> toDeferredResultList(final Flux<T> flux) {
        return toDeferredResult(flux.collectList());
    }

    public static <T> DeferredResult<Set<T>> toDeferredResultSet(final ParallelFlux<T> flux) {
        return new ParallelFluxResult<>(flux);
    }

    public static <T> Flux<T> fromCloseableStream(final Stream<T> stream) {
        return Flux.fromStream(stream).doOnTerminate(stream::close);
    }

    /**
     * Provides a {@code DeferredResult<Set<T>>} implementation based on a {@link ParallelFlux}. Unfortunately,
     * {@link ParallelFlux#reduce(Supplier, BiFunction)} does not provide a proper type signature to allow for a return
     * value of {@code DeferredResult<Set<T>>} to hide the implementation class.
     */
    private static class ParallelFluxResult<T> extends DeferredResult<Set<T>> {
        private final Set<T> set = ConcurrentHashMap.newKeySet();

        private ParallelFluxResult(final ParallelFlux<T> flux) {
            super();
            flux.subscribe(set::add, this::setErrorResult, () -> this.setResult(set));
        }
    }

}
