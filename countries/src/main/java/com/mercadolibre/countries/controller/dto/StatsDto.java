package com.mercadolibre.countries.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public record StatsDto(
	@JsonProperty("min_distance")
	DistanceStat minDistance,
	@JsonProperty("max_distance")
	DistanceStat maxDistance,
	@JsonProperty("avg_distance")
	Long avgDistance
) {
}
