package pubsub;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class StreamConsumer<T> implements Subscriber<T> {

    @Override
    public void onSubscribe(Subscription subscription) {

    }

    @Override
    public void onNext(T t) {
        System.out.println(t);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("Error: " + throwable);
    }

    @Override
    public void onComplete() {
        System.out.println("Completed");
    }
}
