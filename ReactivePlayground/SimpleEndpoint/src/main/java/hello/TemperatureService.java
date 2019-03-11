package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class TemperatureService {

    Logger logger = LoggerFactory.getLogger(TemperatureController.class);

    public Integer getRandNumber() {
        Random r = new Random();
        int low = 0;
        int high = 50;

        return r.nextInt(high - low) + low;
    }
}
