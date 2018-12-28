package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class Controller {

    @GetMapping("/")
    public String get() {
        return Instant.now().toString();
    }

    @GetMapping("/health")
    public String getTekst() {
        return "I'm alive";
    }
}
