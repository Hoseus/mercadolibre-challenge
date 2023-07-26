package com.mercadolibre.countries.service.ipapi;

import com.fasterxml.jackson.annotation.JsonProperty;

public record IpApiDto(
	Boolean success,
	@JsonProperty("country_code")
	String countryCode,
	@JsonProperty("country_name")
	String countryName,
	String latitude,
	String longitude,
	IpApiError error
) {
}
