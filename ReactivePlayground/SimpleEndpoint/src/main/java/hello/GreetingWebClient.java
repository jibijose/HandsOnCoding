package hello;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class GreetingWebClient {

    public String getResult(int seconds) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://localhost:9090/hellosecond/" + seconds, String.class);
    }

    public Mono<String> getResultStream(int seconds) {
        WebClient client = WebClient.create("http://localhost:9090");
        Mono<ClientResponse> result = client.get()
                .uri("/hellosecond/" + seconds)
                .accept(MediaType.TEXT_PLAIN)
                .exchange();

        return result.flatMap(res -> res.bodyToMono(String.class));
    }

}
