package com.in28minutes.microservices.currencyexchangeservice;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/sample-api")
    //@Retry(name = "default")
    //@Retry(name = "sample-api") // 10 attempts
    //@Retry(name = "simple-api", fallbackMethod = "hardcodedResponse")
    //@CircuitBreaker(name = "default", fallbackMethod = "hardcodedResponse")
    @RateLimiter(name = "default") // 10s => 1000 calls to the sample api
    public String sampleApi() {

        logger.info("Sample api call received");

        ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy", String.class);

        return forEntity.getBody();
    }

    public String hardcodedResponse(Exception e) {
        return "fallback-response";
    }
}
