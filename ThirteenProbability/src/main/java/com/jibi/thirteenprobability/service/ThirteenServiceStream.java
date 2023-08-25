package com.jibi.thirteenprobability.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
@Slf4j
public class ThirteenServiceStream {

    public static int NUMLENGTH = 10;

    long totalNumHasSumMatch = 0;
    long totalNumContainsMatch = 0;
    long totalNumHasSumAndContainsMatch = 0;
    long totalNumbersChecked = 0;

    public void findMatches(boolean isParallel) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        totalNumbersChecked = (long) Math.pow(10.0, (double) NUMLENGTH);

        totalNumHasSumMatch = generateNumberStream(isParallel)
                .filter(hasSumThirteen())
                //.peek(numArray -> System.out.println("Sum Match Number = " + Arrays.toString(numArray)))
                .count();

        totalNumContainsMatch = generateNumberStream(isParallel)
                .filter(containNumberThirteen())
                //.peek(numArray -> System.out.println("Contains Match Number = " + Arrays.toString(numArray)))
                .count();

        totalNumHasSumAndContainsMatch = generateNumberStream(isParallel)
                .filter(hasSumThirteen())
                .filter(containNumberThirteen())
                //.peek(numArray -> System.out.println("Sum and contains Match Number = " + Arrays.toString(numArray)))
                .count();


        if (isParallel) {
            System.out.println("*******************************Stream Parallel***********************************");
        } else {
            System.out.println("*******************************Stream NonParallel********************************");
        }
        System.out.println("Total Numbers has 13 sum matched [" + totalNumHasSumMatch + "/" + totalNumbersChecked + "]   " + (totalNumHasSumMatch * 100.0 / totalNumbersChecked) + "%");
        System.out.println("Total Numbers contains 13 match [" + totalNumContainsMatch + "/" + totalNumbersChecked + "]   " + (totalNumContainsMatch * 100.0 / totalNumbersChecked) + "%");
        System.out.println("Total Numbers has sum 13 and contains 13 match [" + totalNumHasSumAndContainsMatch + "/" + totalNumbersChecked + "]   " + (totalNumHasSumAndContainsMatch * 100.0 / totalNumbersChecked) + "%");
        System.out.println("*****************************************************************************************");

        stopWatch.stop();
        System.out.println("Total execution milli seconds = " + stopWatch.getTotalTimeMillis());
        System.out.println("*****************************************************************************************");
    }

    private Stream<int[]> generateNumberStream(boolean isParallel) {
        if (isParallel) {
            return IntStream.range(0, (int) Math.pow(10.0, (double) NUMLENGTH))
                    .parallel()
                    .mapToObj(i -> ((Integer) i).toString())
                    .map(numString -> stringToDigits(numString));
        } else {
            return IntStream.range(0, (int) Math.pow(10.0, (double) NUMLENGTH))
                    .mapToObj(i -> ((Integer) i).toString())
                    .map(numString -> stringToDigits(numString));
        }

    }

    private static Predicate<int[]> containNumberThirteen() {
        return numbers -> {
            for (int index = 0; index < (numbers.length - 1); index++) {
                if (numbers[index] == 1 && numbers[index + 1] == 3) {
                    return true;
                }
            }
            return false;
        };
    }

    private static Predicate<int[]> hasSumThirteen() {
        return numbers -> {
            int sumNumber = Arrays.stream(numbers).sum();
            if (sumNumber == 13) {
                return true;
            }
            return false;
        };
    }

    private int[] stringToDigits(String numString) {
        int[] newGuess = new int[numString.length()];
        IntStream.range(0, numString.length()).forEach(i -> {
            newGuess[i] = numString.charAt(i) - '0';
        });
        return newGuess;
    }
}