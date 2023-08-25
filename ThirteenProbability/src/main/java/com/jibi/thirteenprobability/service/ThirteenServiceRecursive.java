package com.jibi.thirteenprobability.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.Arrays;

@Service
@Slf4j
public class ThirteenServiceRecursive {

    public boolean findContainNumberThirteen(int[] number) {
        for (int index = 0; index < (NUMLENGTH - 1); index++) {
            if (number[index] == 1 && number[index + 1] == 3) {
                return true;
            }
        }
        return false;
    }

    public boolean findHasSumThirteen(int[] number) {
        int sumNumber = Arrays.stream(number).sum();
        if (sumNumber == 13) {
            return true;
        }
        return false;
    }

    int NUMLENGTH = 10;
    int[] number = new int[NUMLENGTH];

    public void findMatches() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        int numIndex = 0;
        for (int index = 0; index < numValues.length; index++) {
            number[numIndex] = numValues[index];
            //System.out.println("Current number = " + Arrays.toString(number));
            constructNumber(numIndex + 1);
        }
        System.out.println("*******************************Recursive Mode***********************************");
        System.out.println("Total Numbers has 13 sum matched [" + totalNumHasSumMatch + "/" + totalNumbersChecked + "]   " + (totalNumHasSumMatch * 100.0 / totalNumbersChecked) + "%");
        System.out.println("Total Numbers contains 13 match [" + totalNumContainsMatch + "/" + totalNumbersChecked + "]   " + (totalNumContainsMatch * 100.0 / totalNumbersChecked) + "%");
        System.out.println("Total Numbers has sum 13 and contains 13 match [" + totalNumHasSumAndContainsMatch + "/" + totalNumbersChecked + "]   " + (totalNumHasSumAndContainsMatch * 100.0 / totalNumbersChecked) + "%");
        System.out.println("*****************************************************************************************");

        stopWatch.stop();
        System.out.println("Total execution milli seconds = " + stopWatch.getTotalTimeMillis());
        System.out.println("*****************************************************************************************");
    }

    int[] numValues = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    int totalNumHasSumMatch = 0;
    int totalNumContainsMatch = 0;
    int totalNumHasSumAndContainsMatch = 0;
    int totalNumbersChecked = 0;

    private void constructNumber(int numIndex) {
        if (numIndex >= NUMLENGTH) {
            if (findHasSumThirteen(number)) {
                totalNumHasSumMatch++;
                //System.out.println("Sum Match Number = " + Arrays.toString(number));
            }
            if (findContainNumberThirteen(number)) {
                totalNumContainsMatch++;
                //System.out.println("Contains Match Number = " + Arrays.toString(number));
            }
            if (findHasSumThirteen(number) && findContainNumberThirteen(number)) {
                totalNumHasSumAndContainsMatch++;
                //System.out.println("Sum and contains Match Number = " + Arrays.toString(number));
            }
            totalNumbersChecked++;
            return;
        }
        for (int index = 0; index < numValues.length; index++) {
            number[numIndex] = numValues[index];
            //System.out.println("Current number = " + Arrays.toString(number));
            constructNumber(numIndex + 1);
        }
    }
}
