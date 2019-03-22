package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.Logger;
import reactor.util.Loggers;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

@Service
public class GreetingService {

    private static Logger LOGGER = Loggers.getLogger(GreetingService.class);

    @Autowired
    private GreetingWebClient greetingWebClient;


    public Flux<String> factorialResultStream(int maxPrimeCheckNum) {
        int[] mynums = IntStream.rangeClosed(1, maxPrimeCheckNum).toArray();
        List<Integer> randNumbers = new ArrayList<Integer>();
        Collections.addAll(randNumbers, Arrays.stream(mynums).boxed().toArray(Integer[]::new));

        Flux<Integer> integerFlux = Flux.fromIterable(randNumbers);

        return integerFlux
                //.publishOn(Schedulers.parallel())
                .flatMap(number -> Mono.just(number)
                                //.publishOn(Schedulers.parallel())
                                //.map(num -> cpuWork(num))
                                .from(greetingWebClient.getResultStream(number))
                        //.map(num -> greetingWebClient.getResult(num))
                );
    }

    public String factorialResults(int maxPrimeCheckNum) throws Exception {
        int[] mynums = IntStream.rangeClosed(1, maxPrimeCheckNum).toArray();
        List<Integer> randNumbers = new ArrayList<Integer>();
        Collections.addAll(randNumbers, Arrays.stream(mynums).boxed().toArray(Integer[]::new));

        return getResults(randNumbers, true);
    }

    private String getResults(List<Integer> randNumbers, Boolean useFuture) throws Exception {
        if (useFuture) {
            return getResultsFuture(randNumbers);
        }

        return getResultsBlocked(randNumbers);
    }

    private String getResultsBlocked(List<Integer> randNumbers) {
        StringBuilder sb = new StringBuilder("");
        for (Integer randNumber : randNumbers) {
            sb.append(greetingWebClient.getResult(randNumber.intValue()));
        }
        return sb.toString();
    }

    private String getResultsFuture(List<Integer> randNumbers) throws Exception {
        StringBuilder sb = new StringBuilder("");
        CompletableFuture<String> completableFutures[] = new CompletableFuture[randNumbers.size()];
        int index = 0;
        for (Integer randNumber : randNumbers) {
            completableFutures[index] = CompletableFuture.supplyAsync(() -> greetingWebClient.getResult(randNumber.intValue()));
            index++;
        }

        index = 0;
        for (Integer randNumber : randNumbers) {
            sb.append(completableFutures[index].get());
            index++;
        }
        return sb.toString();
    }

    private String cpuWork(int n) {
        BigInteger factorial = findFactorial(new BigInteger(new Integer(n).toString()));
        String result = "Factorial of " + n + " is " + factorial.toString();
        //LOGGER.info(result);
        return result;
    }

    private BigInteger findFactorial(BigInteger product) {
        if (product.intValue() < 2) {
            return product;
        }
        return findFactorial(product.subtract(new BigInteger("1"))).multiply(product);
    }

}
