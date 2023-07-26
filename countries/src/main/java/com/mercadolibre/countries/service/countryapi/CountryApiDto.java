package com.mercadolibre.countries.service.countryapi;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Map;

public record CountryApiDto(
	@JsonAnyGetter
	Map<String, CountryData> countriesData
) {
	@JsonCreator
	public CountryApiDto {
	}
}
