package com.mercadolibre.countries.service.exchangerateapi;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Map;

public record ExchangeRateApiDto(
	String result,
	@JsonProperty("base_code")
	String base,
	@JsonProperty("conversion_rates")
	Map<String, BigDecimal> rates,
	@JsonProperty("error-type")
	String errorType
) {
	@JsonIgnore
	public BigDecimal getRate(String currency) {
		return this.rates.getOrDefault(currency, null);
	}
}
