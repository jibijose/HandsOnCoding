package pubsub;


import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class StreamPublisher<T> implements Publisher<T> {

    private final Supplier<Stream<T>> streamSupplier;

    public StreamPublisher(Supplier<Stream<T>> streamSupplier) {
        this.streamSupplier = streamSupplier;
    }

    @Override
    public void subscribe(Subscriber<? super T> subscriber) {
        try {
            Stream<T> stream = streamSupplier.get();
            stream.forEach(subscriber::onNext);
            subscriber.onComplete();
        } catch (Throwable e) {
            subscriber.onError(e);
        }
    }

}
