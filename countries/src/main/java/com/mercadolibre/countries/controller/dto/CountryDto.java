package com.mercadolibre.countries.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.runtime.annotations.RegisterForReflection;

import java.util.List;
import java.util.Map;

@RegisterForReflection
public record CountryDto(
	String name,
	String alpha2,
	String alpha3,
	List<String> languages,
	// Key = timezone, Value = time
	Map<String, String> times,
	@JsonProperty("distance_to_buenos_aires")
	Long distanceToBuenosAires,
	List<Currency> currencies
) {
}
