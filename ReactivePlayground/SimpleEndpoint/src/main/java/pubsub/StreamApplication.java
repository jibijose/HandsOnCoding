package pubsub;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class StreamApplication {

    public static void main(String[] args) {
        Supplier<Stream<Integer>> supplier = () -> Stream.of(1, 2, 3, 4, 5, 6);
        StreamPublisher<Integer> streamPublisher = new StreamPublisher<Integer>(supplier);
        streamPublisher.subscribe(new StreamConsumer<>());
        System.out.println("Completed!!!");
    }
}
