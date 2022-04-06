package com.gagan.microservices.currencyexchangeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrenyExchangeController {

    @Autowired
    private Environment environment;

    @GetMapping("/curreny-exchange/from/{from}/to/{to}")
    public CurrenyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
        CurrenyExchange currenyExchange = new CurrenyExchange(1000L, "USD", "INR", BigDecimal.valueOf(70));
        String port = environment.getProperty("local.server.port");
        currenyExchange.setEnvironment(port);

        return currenyExchange;
    }
}
