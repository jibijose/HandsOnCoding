package com.jibi.thirteenprobability;

import com.jibi.thirteenprobability.service.ThirteenServiceStream;
import com.jibi.thirteenprobability.service.ThirteenServiceRecursive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ThirteenprobabilityApplication implements ApplicationRunner {

    @Autowired
    private ThirteenServiceRecursive thirteenServiceRecursive;
    @Autowired
    private ThirteenServiceStream thirteenServiceStream;

    public static void main(String[] args) {
        SpringApplication.run(ThirteenprobabilityApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments arg0) throws Exception {
        System.out.println("Application Runner started");
        thirteenServiceRecursive.findMatches();
        thirteenServiceStream.findMatches(false);
        thirteenServiceStream.findMatches(true);
        System.out.println("Application Runner stopped");
    }

}
