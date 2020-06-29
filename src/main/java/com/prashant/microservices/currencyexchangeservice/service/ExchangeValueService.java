package com.prashant.microservices.currencyexchangeservice.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prashant.microservices.currencyexchangeservice.pojo.ExchangeValue;

public interface ExchangeValueService extends JpaRepository<ExchangeValue, Long>{

	public ExchangeValue findByFromAndTo(String from, String to);
}
