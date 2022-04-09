package com.gagan.microservices.currencyexchangeservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyExchangeRepository extends JpaRepository<CurrenyExchange, Long> {

    CurrenyExchange findByFromAndTo(String from, String to);
}
