package com.mercadolibre.countries.controller;

import com.mercadolibre.countries.controller.dto.StatsDto;
import com.mercadolibre.countries.service.StatsService;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/v1/stats")
public class StatsController {
	private final StatsService statsService;

	public StatsController(StatsService statsService) {
		this.statsService = statsService;
	}

	@GET
	public Uni<StatsDto> get() {
		return Uni.createFrom().voidItem()
			.invoke(() -> Log.info("Start. Get stats"))
			.chain(this.statsService::get)
			.invoke(response -> Log.infof("End. Get stats. Response: %s", response));
	}
}
