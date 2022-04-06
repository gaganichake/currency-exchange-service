package com.gagan.microservices.currencyexchangeservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrenyExchangeController {

    @GetMapping("/curreny-exchange/from/{from}/to/{to}")
    public CurrenyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
        return new CurrenyExchange(1000L, "USD", "INR", BigDecimal.valueOf(70));
    }
}
