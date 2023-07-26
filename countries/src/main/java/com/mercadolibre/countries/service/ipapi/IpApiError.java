package com.mercadolibre.countries.service.ipapi;
public record IpApiError(
	long code,
	String type,
	String info
) {
}
