package com.mercadolibre.countries.service.countryapi;

import java.util.List;
import java.util.Map;

public record CountryData(
	String name,
	String alpha2Code,
	String alpha3Code,
	LatLng latLng,
	List<String> timezones,
	Map<String, Currency> currencies,
	Map<String, String> languages
) {
}
