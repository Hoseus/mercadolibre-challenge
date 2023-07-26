package com.mercadolibre.countries.producer;

public record StatsMessage(
	String country,
	Long distanceToBuenosAires
) {
}
