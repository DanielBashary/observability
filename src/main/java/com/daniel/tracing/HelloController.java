package com.daniel.tracing;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class HelloController {
    private final RestTemplate restTemplate;
    private final WebClient webClient;
    public HelloController(RestTemplate restTemplate, WebClient webClient) {
        this.restTemplate = restTemplate;
        this.webClient = webClient;
    }

    @GetMapping("/hello")
    public String hello(){
        return restTemplate.postForEntity("https://httpbin.org/post", "Hello, Cloud!", String.class)
                .getBody();
    }

    @GetMapping("/hello-web")
    public Mono<String> helloWeb(){
        return webClient.post()
                .uri("https://httpbin.org/post")
                .bodyValue("Hello, Cloud!")
                .retrieve()
                .bodyToMono(String.class);
    }
}
