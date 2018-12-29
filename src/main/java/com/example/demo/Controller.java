package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

@RestController
public class Controller {

    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/")
    public String get() {
        return Instant.now().toString();
    }

    @GetMapping("/health")
    public String getTekst() {
        return "I'm alive";
    }

    @GetMapping("/api-gateway")
    public ResponseEntity<String> getHealthApiGateway() {
        System.out.println("Sending get to api-gateway");
        ResponseEntity<String> forEntity = restTemplate.getForEntity("be-api-gateway:8081/api/healthcheck", String.class);
        System.out.println("Sending get to api-gateway: " + forEntity.getBody());
        return forEntity;
    }
}
