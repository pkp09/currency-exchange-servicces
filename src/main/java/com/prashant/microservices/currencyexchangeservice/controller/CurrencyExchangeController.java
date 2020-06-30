package com.prashant.microservices.currencyexchangeservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.prashant.microservices.currencyexchangeservice.pojo.ExchangeValue;
import com.prashant.microservices.currencyexchangeservice.service.ExchangeValueService;

@SpringBootApplication
@RestController
public class CurrencyExchangeController {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private Environment environment;
	
	@Autowired
	private ExchangeValueService repoService;

	/**
	* from map to USD
	* and to map to INR
	*/
	@GetMapping("/currency-exchange/from/{from}/to/{to}") // where {from} and {to} are path variable
	public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) 
	{
		// hard-coded value
		// ExchangeValue exchangeValue = new ExchangeValue(1000L, from, to, BigDecimal.valueOf(65));
		
		// fetching from DB
		ExchangeValue exchangeValue = repoService.findByFromAndTo(from, to);
		// picking port from the environment
		exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		
		logger.debug("CurrencyExchangeController.retrieveExchangeValue() -> exchangeValue -> {} : ", exchangeValue);
		return exchangeValue;
	}
}