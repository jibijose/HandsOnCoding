package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Greetings {

    private static Logger LOGGER = LoggerFactory.getLogger(Greetings.class);

    @GetMapping(value = "/hellosecond/{seconds}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String helloOld(@PathVariable(value = "seconds") String seconds) {
        LOGGER.info("Sleeping " + seconds + " seconds.");
        try {
            Thread.sleep(Integer.parseInt(seconds) * 1000);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        String returnValue = "Slept " + seconds + " seconds.";
        LOGGER.info(returnValue);
        return returnValue;
    }


}
