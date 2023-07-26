package com.mercadolibre.countries.service.exchangerateapi;

public record ExchangeRateApiError(
	int code,
	String type,
	String info
) {
}
