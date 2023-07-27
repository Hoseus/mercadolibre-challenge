package com.mercadolibre.countries.service.countryapi;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mercadolibre.countries.service.countryapi.serialization.NotAvailableJacksonDeserializer;

import java.util.List;
import java.util.Map;

public record CountryData(
	String name,
	String alpha2Code,
	String alpha3Code,
	LatLng latLng,
	List<String> timezones,
	Map<String, Currency> currencies,

	//The json response could have an object or a string saying "N/A" here. That's why the custom deserializer.
	//In case of string this will be set to null.
	@JsonDeserialize(using = NotAvailableJacksonDeserializer.class)
	Map<String, String> languages
) {
}
