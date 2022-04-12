package com.gagan.microservices.currencyexchangeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrenyExchangeController {

    @Autowired
    private CurrencyExchangeRepository repository;

    @Autowired
    private Environment environment;

    @GetMapping("/curreny-exchange/from/{from}/to/{to}")
    public CurrenyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
        CurrenyExchange currenyExchange = repository.findByFromAndTo(from, to);
        if(currenyExchange == null) {
            throw new RuntimeException("Unable to find data for " + from + " to " + to);
        }
        String port = environment.getProperty("local.server.port");
        currenyExchange.setEnvironment(port);

        return currenyExchange;
    }
}
