package com.mercadolibre.stats.consumer;

public record StatsMessage(
	String country,
	Long distanceToBuenosAires
) {
}
