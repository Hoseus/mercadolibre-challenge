package com.mercadolibre.countries.controller;

import com.mercadolibre.countries.controller.dto.CountryDto;
import com.mercadolibre.countries.service.CountryService;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/v1/countries")
public class CountryController {
	private final CountryService countryService;

	public CountryController(CountryService countryService) {
		this.countryService = countryService;
	}

	@GET
	@Path("/{ip}")
	public Uni<CountryDto> getByIp(@PathParam("ip") String ip) {
		return
			Uni.createFrom().item(ip)
				.invoke(it -> Log.infof("Start. Get country by Ip. Ip: %s", it))
				.flatMap(this.countryService::getByIp)
				.invoke(response -> Log.infof("End. Get country by Ip. Response: %s", response));
	}
}
