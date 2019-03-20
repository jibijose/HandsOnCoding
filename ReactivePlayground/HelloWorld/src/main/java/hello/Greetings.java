package hello;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Greetings {

    @GetMapping(value = "/helloold/{maxPrimeCheckNum}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String helloOld(@PathVariable(value = "maxPrimeCheckNum") String maxPrimeCheckNum) {
/*        try {
            Thread.sleep(100);
        } catch (Exception exception) {
            exception.printStackTrace();
        }*/
        return "jibijose";
    }


}
