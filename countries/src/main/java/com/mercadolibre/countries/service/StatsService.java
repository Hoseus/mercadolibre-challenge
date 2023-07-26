package com.mercadolibre.countries.service;

import com.mercadolibre.countries.controller.dto.DistanceStat;
import com.mercadolibre.countries.controller.dto.StatsDto;
import com.mercadolibre.countries.repository.StatsRepository;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StatsService {
	private final StatsRepository statsRepository;

	public StatsService(
		StatsRepository statsRepository
	) {
		this.statsRepository = statsRepository;
	}

	public Uni<StatsDto> get() {
		return this.statsRepository.get()
			.map(statsEntity ->
				new StatsDto(
					new DistanceStat(statsEntity.minDistanceCountry(), statsEntity.minDistanceToBuenosAires()),
					new DistanceStat(statsEntity.maxDistanceCountry(), statsEntity.maxDistanceToBuenosAires()),
					statsEntity.avgDistanceToBuenosAires()
				)
			)
			.onFailure()
			.invoke(ex -> Log.error("Error", ex));
	}
}
