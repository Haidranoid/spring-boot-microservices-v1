package com.in28minutes.microservices.currencyconversionservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "currency-exchange-service", url = "localhost:8000")
@FeignClient(name = "currency-exchange-service") // enables load balance
public interface CurrencyExchangeProxy {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    CurrencyConversion retrieveExchangeValue(
            @PathVariable String from,
            @PathVariable String to
    );
}
