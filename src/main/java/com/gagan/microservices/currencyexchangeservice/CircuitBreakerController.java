package com.gagan.microservices.currencyexchangeservice;

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

    private final Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    @GetMapping("sample-api-demo-retry")
    @Retry(name = "sample-api", fallbackMethod = "aHardCodedFallbackMethod")
    public String sampleApiForRetry() {
        logger.info("Sample API call received - /sample-api-demo-retry");
        ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://locahost:8080/non-working-app", String.class);
        return forEntity.getBody();
    }

    @GetMapping("sample-api-demo-circuit-breaker")
    @CircuitBreaker(name = "sample-api", fallbackMethod = "aHardCodedFallbackMethod")
    public String sampleApiForCircuitBreaker() {
        logger.info("Sample API call received - /sample-api-demo-circuit-breaker");
        ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://locahost:8080/non-working-app", String.class);
        return forEntity.getBody();
    }

    @GetMapping("sample-api-demo-rate-limiter")
    @RateLimiter(name = "sample-api")
    public String sampleApiForRateLimiter() {
        logger.info("Sample API call received - /sample-api-demo-rate-limiter");
        return "A Hard Coded Response";
    }


    public String aHardCodedFallbackMethod(Throwable throwable) {
        return "A Hard Coded Fallback Response";
    }
}
