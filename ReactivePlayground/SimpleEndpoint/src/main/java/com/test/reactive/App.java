package com.test.reactive;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

public class App {

    public static void main(String[] args) {
        App app = new App();
        app.reactiveInfinite();
    }

    private void reactiveInfinite() {
        System.out.println("Hello World Start!");
        ConnectableFlux<Object> publish = Flux.create(fluxSink -> {
            while(true) {
                long ctm = System.currentTimeMillis();
                //System.out.println(new Date() +  " Next " + ctm);
                fluxSink.next(ctm);
                //try { Thread.sleep(10); } catch(InterruptedException ie) { ie.printStackTrace(); }
            }
        })
                //.delayElements(Duration.ofSeconds(1))
                .publish();

        Consumer<Object> consumer = obj -> System.out.println(Thread.currentThread().getName() + " " + obj);
        publish.subscribeOn(Schedulers.parallel()).subscribe(consumer);
        publish.connect();

        System.out.println("Hello World End!");
    }

    private void myFunction(Object obj) {

    }

    private void reactiveWithBackPressure() {
        System.out.println("Hello World Start!");
        List<Integer> elements = new ArrayList<>();

        Flux.just(1, 2, 3, 4, 5)
                .log()
                .subscribe(new Subscriber<Integer>() {
                    private Subscription s;
                    //int onNextAmount;

                    @Override
                    public void onSubscribe(Subscription s) {
                        this.s = s;
                        //s.request(2);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        elements.add(integer);
                        //onNextAmount++;
                        //if (onNextAmount % 2 == 0) {
                        //    s.request(2);
                        //}
                    }

                    @Override
                    public void onError(Throwable t) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });

        System.out.println("Hello World End!");
    }

    public static void logMe(Object obj) {
        System.out.println(obj);
    }
}